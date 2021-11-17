package br.pro.celsofurtado.firebaseapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import br.pro.celsofurtado.firebaseapp.databinding.ActivityMainBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.btnLer.setOnClickListener {
            val intent = Intent(this, LerDadosActivity::class.java)
            startActivity(intent)
        }


        binding.btnSalvar.setOnClickListener {

            val nome = binding.editNome.text.toString()
            val email = binding.editEmail.text.toString()
            val telefone = binding.editFone.text.toString()

            // ** conecta com o Realtime database no Firebase
            database = FirebaseDatabase.getInstance().getReference("Users")

            // ** Criar permissões para teste
            val permissoes = listOf<Permissao>(
                Permissao(1, "Acessar Cadastro"),
                Permissao(2, "Cadastrar produto"),
                Permissao(3, "Atualizar produto")
            )

            // ** cria o objeto que será persistido
            val user = User(nome, email, telefone, permissoes)

            // ** define quem será o nó filho
            database.child(nome).setValue(user).addOnSuccessListener {

                binding.editNome.text.clear()
                binding.editEmail.text.clear()
                binding.editFone.text.clear()

                Toast.makeText(this, "Usuário gravado com sucesso!!", Toast.LENGTH_SHORT).show()

            }.addOnFailureListener {

                Toast.makeText(this, "ERRO!!", Toast.LENGTH_SHORT).show()

            }



        }
    }
}