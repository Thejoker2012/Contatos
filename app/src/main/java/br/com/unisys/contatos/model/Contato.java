package br.com.unisys.contatos.model;

import java.io.Serializable;

public class Contato  implements Serializable {

    public static final String TABLE_NAME = "contato";
    public static final String ID = "id";
    public static final String NOME = "nome";
    public static final String TELEFONE = "telefone";

    public static final String DATABASE_CREATE = "create table"
            + TABLE_NAME +  "("
            + ID +          "integer primary key autoincrement,"
            + NOME +        "text not null,"
            + TELEFONE +    "text not null );";

    private int _id;
    private String nome;
    private String telefone;


    // construtor vazio
    public Contato(){
    }
    public Contato(int id, String nome, String telefone){
        this._id = id;
        this.nome = nome;
        this.telefone = telefone;
    }
    public Contato(String nome, String telefone){
        this.nome = nome;
        this.telefone = telefone;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

}
