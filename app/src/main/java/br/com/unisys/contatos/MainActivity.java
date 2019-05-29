package br.com.unisys.contatos;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import br.com.unisys.contatos.dao.ContatoDAO;
import br.com.unisys.contatos.model.Contato;
import br.com.unisys.contatos.ui.adapter.ContatoAdapter;
import br.com.unisys.contatos.ui.adapter.ContatoForm;

public class MainActivity extends AppCompatActivity {

    private List<Contato> contatoList = null;

    private ContatoAdapter contatoAdapter;
    private ListView contatoListView;

    private ContatoDAO contatoDAO = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, ContatoForm.class);
                startActivity(i);
            }
        });

        contatoListView = (ListView) findViewById(R.id.lista_aluno);

        contatoDAO = new ContatoDAO(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        contatoList = contatoDAO.getAllContatos();
        if (!contatoList.isEmpty() && contatoList.size() > 0) {
            contatoAdapter = new ContatoAdapter(contatoList, this);
            contatoListView.setAdapter(contatoAdapter);
        } else {
            Toast.makeText(getApplicationContext(), "Não tem contatos cadastrados" , Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
