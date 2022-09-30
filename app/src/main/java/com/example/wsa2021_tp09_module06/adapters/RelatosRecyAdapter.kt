package com.example.wsa2021_tp09_module06.adapters

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.wsa2021_tp09_module06.R
import com.example.wsa2021_tp09_module06.databinding.ConfirmYesNoBinding
import com.example.wsa2021_tp09_module06.databinding.ItemRelatoBinding
import com.example.wsa2021_tp09_module06.helper.IServices
import com.example.wsa2021_tp09_module06.helper.IServices.Delete
import com.example.wsa2021_tp09_module06.helper.Singleton
import com.example.wsa2021_tp09_module06.model.Relatos
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException
import kotlin.math.log

class RelatosRecyAdapter(val activity: Activity, var list: ArrayList<Relatos>) :
    RecyclerView.Adapter<RelatosRecyAdapter.viewHolder>() {

    inner class viewHolder(val binding: ItemRelatoBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        var binding = ItemRelatoBinding.bind(
            LayoutInflater.from(parent.context).inflate(R.layout.item_relato, parent, false)
        )
        return viewHolder(binding)
    }

    override fun onBindViewHolder(holder: viewHolder, @SuppressLint("RecyclerView") position: Int) {
        with(holder.binding) {
            with(list.get(position)) {
                txtRelato.setText(nome)
                txtTel.setText(telefone)
                if(txtRelato.text.toString()=="null"){
                    txtRelato.setText("Anonimo")
                    txtTel.setText("anonimo")
                }
                txtlotitud.setText(latitude.toString())
                txtlotitudde.setText(longitude.toString())
                holder.binding.root.setOnClickListener {

                    Singleton.relatos = list.get(position)
                    holder.itemView.findNavController().navigate(R.id.detalleRelatoFragment)
                }

                holder.binding.root.setOnLongClickListener {
                    var bindingAlert = ConfirmYesNoBinding.bind(
                        LayoutInflater.from(holder.itemView.context)
                            .inflate(R.layout.confirm_yes_no, null, false)
                    )


                    var aler = AlertDialog.Builder(holder.itemView.context)
                        .setView(bindingAlert.root)
                        .create()
                    aler.show()
                    bindingAlert.btnNo.setOnClickListener {
                        aler.hide()
                    }
                    bindingAlert.btnYes.setOnClickListener {

                        aler.hide()
                        IServices.okhttp.newCall(Delete("api/relatos/$id")).enqueue(

                            object : Callback{
                                override fun onFailure(call: Call, e: IOException) {
                                    TODO("Not yet implemented")
                                }

                                override fun onResponse(call: Call, response: Response) {

                                    activity.runOnUiThread {
                                        Toast.makeText(activity.applicationContext,"has been removed successful",Toast.LENGTH_LONG).show()
                                        list.remove(list.get(position))
                                        notifyDataSetChanged()
                                    }



                                }
                            }

                        )
                    }
                    return@setOnLongClickListener true
                }
                try {
                    var context = holder.itemView.context

                    var url = context.resources.getIdentifier(
                        "@drawable/v${imagem.replace(".jgp", "")}",
                        null,
                        context.packageName
                    )
                    imageView2.setImageDrawable(
                        ContextCompat.getDrawable(
                            holder.itemView.context,
                            url
                        )
                    )
                } catch (e: java.lang.Exception) {
                    Log.e("Error", "onBindViewHolder: ${e.message.toString()} ")
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}