package br.com.unisys.contatos.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.unisys.contatos.R;
import br.com.unisys.contatos.model.Contato;

public class ContatoAdapter extends BaseAdapter {
    private List<Contato> contatosList = null;
    private Context context = null;

    public ContatoAdapter(List<Contato> contatosList,Context context){
        this.contatosList = contatosList;
        this.context = context;
    }

    @Override
    public int getCount() {
        if(!contatosList.isEmpty()){
            return contatosList.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if(!contatosList.isEmpty()){
            return contatosList.get(position);
        }
        return 0;
    }

    @Override
    public long getItemId(int position) {
        if(!contatosList.isEmpty()){
            return contatosList.get(position).get_id();
        }
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Contato contato = contatosList.get(position);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.contato_item,null);

        TextView contatoDesc = (TextView) view.findViewById(R.id.contato_desc);

        StringBuffer sb = new StringBuffer(1200);
        sb.append(contato.getNome());
        sb.append("-");
        sb.append(contato.getTelefone());
        contatoDesc.setText(sb.toString());
        return view;
    }
}
