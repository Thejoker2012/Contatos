package br.com.unisys.contatos.ui.adapter;

import android.support.v7.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.unisys.contatos.MainActivity;
import br.com.unisys.contatos.R;
import br.com.unisys.contatos.dao.ContatoDAO;
import br.com.unisys.contatos.model.Contato;

public class ContatoForm extends AppCompatActivity {

    private ViewHolder viewHolder = new ViewHolder();

    private ContatoDAO contatoDAO = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_contato_form);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ContatoForm.this, MainActivity.class);
                startActivity(i);
            }
        });

        this.viewHolder.nomeEdt = (EditText) findViewById(R.id.nomeEdt);
        this.viewHolder.telefoneEdt = (EditText) findViewById(R.id.telefoneEdt);
        this.viewHolder.salvarBtn = (Button) findViewById(R.id.salvarBtn);

        contatoDAO = new ContatoDAO(this);

        this.viewHolder.salvarBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String nome = ContatoForm.this.viewHolder.nomeEdt.getText().toString();
                String telefone = ContatoForm.this.viewHolder.telefoneEdt.getText().toString();

                Contato contato = criaContato(nome, telefone);
                gravaAluno(contato);
            }

        });
    }

    public void gravaAluno(Contato contato){
        contatoDAO.addContato(contato);
        Toast.makeText(ContatoForm.this,"Contato Cadastrado", Toast.LENGTH_LONG).show();
    }

    private Contato criaContato(String nome, String telefone) {
        Contato contato = new Contato();
        contato.setNome(nome);
        contato.setTelefone(telefone);
        return contato;
    }

    public static class ViewHolder {
        EditText nomeEdt;
        EditText telefoneEdt;
        Button salvarBtn;
        Button listaAlunoBtn;
    }

}
