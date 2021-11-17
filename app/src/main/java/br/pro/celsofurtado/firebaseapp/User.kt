package br.pro.celsofurtado.firebaseapp

data class User(
    val nome: String,
    val email: String,
    val telefone: String,
    val permissoes: List<Permissao>
)
