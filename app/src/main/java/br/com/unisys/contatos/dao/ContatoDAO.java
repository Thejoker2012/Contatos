package br.com.unisys.contatos.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.unisys.contatos.infra.DatabaseHandler;
import br.com.unisys.contatos.model.Contato;

public class ContatoDAO {

    private DatabaseHandler databaseHandler = null;

    private String[] allColumns = {Contato.ID,Contato.NOME,Contato.TELEFONE};

    public ContatoDAO(Context context){
        databaseHandler = new DatabaseHandler(context);
    }

    // adiciona novo contato
    public void addContato(Contato contato) {

        //abre o banco para leitura e gravação
        SQLiteDatabase database = databaseHandler.getWritableDatabase();

        //cria o objeto para preencher valores nas tabelas
        ContentValues values = new ContentValues();
        values.put(Contato.NOME, contato.getNome());  // Contato Nome
        values.put(Contato.TELEFONE, contato.getTelefone());  // Contato Telefone

        database.insert(Contato.TABLE_NAME, null, values);// Inserindo a linha
        database.close(); // Fechando a conexão do banco.
    }


    // paga um simples contato por id
    public Contato getContato(int id) {

        //abre o banco para leitura
        SQLiteDatabase database = databaseHandler.getReadableDatabase();

        Cursor cursor = database.query(Contato.TABLE_NAME,allColumns,Contato.ID +" = "+
                id,null,null,null,null);
        cursor.moveToFirst();

        Contato contato = new Contato();

        contato.set_id(cursor.getInt(0));
        contato.setNome(cursor.getString(1));
        contato.setTelefone(cursor.getString(2));

        return contato;
    }


    // pega todos os contatos
    public List<Contato> getAllContatos() {

        //abre o banco para leitura
        SQLiteDatabase database = databaseHandler.getReadableDatabase();

        Cursor cursor = database.query(Contato.TABLE_NAME,allColumns,null,null,null,null,null);

        List<Contato> contatos = new ArrayList<>();

        // Pega os vales resultantes e seta no objeto, movendo sempre para o próximo cursor
        if(cursor.moveToFirst()){

            int idxId = cursor.getColumnIndex(Contato.ID);
            int idxNome = cursor.getColumnIndex(Contato.NOME);
            int idxTelefone = cursor.getColumnIndex(Contato.TELEFONE);

            do{
                Contato contato = new Contato();
                contatos.add(contato);

                if (idxId !=0)
                    contato.set_id(cursor.getInt(idxId));
                if (idxNome !=0)
                    contato.set_id(cursor.getInt(idxNome));
                if (idxTelefone !=0)
                    contato.set_id(cursor.getInt(idxTelefone));

            }while (cursor.moveToNext());
        }
        cursor.close();
        return contatos;
    }


    // pega o numero de contatos
    public int getContatosCount() {

        String countQuery = "SELECT * FROM "+ Contato.TABLE_NAME;

        //abre o banco para leitura
        SQLiteDatabase database = databaseHandler.getReadableDatabase();

        //passa a query para ser exetutada
        Cursor cursor = database.rawQuery(countQuery,null);
        cursor.close();

        // retorna o soma total dos contatos
        return cursor.getCount();
    }


    // atualizando o contato
    public int updateContato(Contato contato) {

        //abre o banco para leitura e gravação
        SQLiteDatabase database = databaseHandler.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Contato.NOME,contato.getNome());
        values.put(Contato.TELEFONE,contato.getTelefone());

        // faz update no registo
        return database.update(Contato.TABLE_NAME,values,Contato.ID+" = ?",
                new String[]{String.valueOf(contato.get_id())});
    }


    // deletando o contato
    public void deleteContato(Contato contato) {

        //abre o banco para leitura e gravação
        SQLiteDatabase database = databaseHandler.getWritableDatabase();
        database.delete(Contato.TABLE_NAME,Contato.ID+"=?",
                new String[]{String.valueOf(contato.get_id())});

        database.close();

    }
}
