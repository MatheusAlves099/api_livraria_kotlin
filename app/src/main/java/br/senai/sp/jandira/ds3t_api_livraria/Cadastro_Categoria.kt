package br.senai.sp.jandira.ds3t_api_livraria

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.lifecycleScope
import com.google.gson.JsonObject
import kotlinx.coroutines.launch

class Cadastro_Categoria : AppCompatActivity() {

    //CRIAÇÃO DO ATRIBUTO QUE VAI REPRESENTAR A ApiService
    private lateinit var apiService: ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_categoria)

        //CRIAÇÃO DA INSTANCIA ATIVA DO RETROFIT
        apiService = RetrofitHelper.getInstance().create(ApiService::class.java)

        //RECUPERA O OBJETO DE editText DO FORMULÁRIO DE CADASTRO DE CATEGORIA
        val txtCategoria = findViewById<EditText>(R.id.txtCategoria)

        //TRATA O EVENTO DE CLIQUE OU TOQUE NO BOTÃO CADASTRAR
        findViewById<Button>(R.id.btnCadastrarCategoria).setOnClickListener{

            //RECUPERAR O VALOR DIGITADO PELO USUÁRIO
            val nomeCategoria = txtCategoria.text

            //ENVIA O DADO DE CATEGORIA PARA CADASTRO NA API
            createCategory(nomeCategoria.toString())
        }
    } //FIM DO MÉTODO ONCREATE

    private fun createCategory(nome_categoria: String){

        lifecycleScope.launch {

            //CRIAÇÃO DO CORPO DE DADOS EM FORMATO JSON
            val body = JsonObject().apply {
                addProperty("nome_categoria", nome_categoria)
            }

            //CHAMADA E ENVIO DE DADOS PARA A ROTA DE CADASTRAR CATEGORIA
            val result = apiService.createCategory(body)

            if(result.isSuccessful){
                val msg = result.body()?.get("mensagemStatus")
                Log.e("CREATE-CATEGORY", "STATUS: ${msg}")
            }else{
                Log.e("CREATE-CATEGORY", "STATUS: ${result.message()}")
            }

        }
    }
} //FIM DA CLASSE