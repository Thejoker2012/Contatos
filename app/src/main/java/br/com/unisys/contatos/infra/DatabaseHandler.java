package br.com.unisys.contatos.infra;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import br.com.unisys.contatos.model.Contato;

public class DatabaseHandler extends SQLiteOpenHelper {

    public static final int VERSION_DB = 1;
    public static final String DATABASE_NAME = "contatos.db";
    private static final String TABLE_CONTATO = "tbl_contato";

    private static final String KEY_ID = "id";
    private static final String KEY_NOME = "nome";
    private static final String KEY_TELEFONE = "phone_number";

    public DatabaseHandler(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION_DB);
    }

    // criação das tabelas.
    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_CONTACTS_TABLE = "CREATE TABLE "
                + TABLE_CONTATO + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_NOME + " TEXT,"
                + KEY_TELEFONE + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // deleta a tabela se ela exitir.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTATO);
        // recria a tabela depois de deletar.
        onCreate(db);

    }
}
