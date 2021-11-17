package br.pro.celsofurtado.firebaseapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import br.pro.celsofurtado.firebaseapp.databinding.ActivityLerDadosBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.getValue

class LerDadosActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLerDadosBinding
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //** defimos o binding
        binding = ActivityLerDadosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBuscar.setOnClickListener {

            val nome = binding.editUserName.text.toString()
            
            if (nome.isNotEmpty()) {
                lerDados(nome)
            } else {
                Toast.makeText(this, "Por favor, informe o nome!!", Toast.LENGTH_SHORT).show()
            }
            
        }

    }

    private fun lerDados(nome: String) {

        database = FirebaseDatabase.getInstance().getReference("Users")

        database.child(nome).get().addOnSuccessListener {

            if (it.exists()) {
                val nome = it.child("nome").value
                val email = it.child("email").value
//                val p = it.child("permissoes").getValue<List<Permissao>>()
//
//                Log.i("xpto", p!!.get(0).descricao)

                binding.editUserName.text.clear()
                binding.tvNome.text = nome.toString()
                binding.tvEmail.text = email.toString()

            } else {
                Toast.makeText(this, "Usuário não existe!", Toast.LENGTH_SHORT).show()
            }

        }.addOnFailureListener {
            Toast.makeText(this, "Ocorreu um erro na leitura dos dados!!", Toast.LENGTH_SHORT)
                .show()
        }

    }
}