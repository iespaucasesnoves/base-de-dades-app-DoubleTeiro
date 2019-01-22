package com.example.alumne.appvino;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLException;

public class EditActivity extends Activity implements View.OnClickListener {
    Button buttonAfegir, buttonActualitzar, buttonDelete;
    EditText editNom, editAnada, editTipus, editLloc, editGraduacio, editData, editComentari, editBodega, editDenominacio, editPreu, editValOlfa, editValGusta, editValVisual, editNota, editFoto;
    TextView ID;
    DataSourceVi bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        ID = (TextView) findViewById(R.id.ID);
        editNom = (EditText) findViewById(R.id.editNomVi);
        editAnada= (EditText) findViewById(R.id.editAnada);
        editTipus = (EditText) findViewById(R.id.editTextTipus);
        editLloc = (EditText) findViewById(R.id.editTextLloc);
        editGraduacio = (EditText) findViewById(R.id.editGraduacio);
        editData = (EditText) findViewById(R.id.editData);
        editComentari = (EditText) findViewById(R.id.editComentari);
        editBodega = (EditText) findViewById(R.id.editBodega);
        editDenominacio = (EditText) findViewById(R.id.editDenominacio);
        editPreu = (EditText) findViewById(R.id.editPreu);
        editValOlfa = (EditText) findViewById(R.id.editValOlfa);
        editValGusta = (EditText) findViewById(R.id.editValGusta);
        editValVisual = (EditText) findViewById(R.id.editValVisual);
        editNota = (EditText) findViewById(R.id.editNota);
        editFoto = (EditText) findViewById(R.id.editFoto);

        buttonAfegir = (Button) findViewById(R.id.buttonAfegir);
        buttonAfegir.setOnClickListener(this);
        buttonActualitzar = (Button) findViewById(R.id.buttonActualitzar);
        buttonActualitzar.setOnClickListener(this);
        buttonDelete = (Button) findViewById(R.id.buttonDelete);
        buttonDelete.setOnClickListener(this);

        Bundle bundle = getIntent().getExtras();
        String id = bundle.getString("ID");
        ID.setText(id);

        if (!id.equals("")){
            try {
                bd.open();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            Vi vi = bd.getVi(Integer.parseInt(id));
            RellenarVino(vi);
            ActualizarVino();
        } else {
            InsertarVi();
        }
    }

    @Override
    public void onClick(View v) {

        if (v == buttonAfegir){
            InsertarVi();
        }

        else if (v == buttonActualitzar){
            ActualizarVino();
        }

        else if (v == buttonDelete){
            DeleteVino();
        }
    }
    public void InsertarVi(){
        String value= editNota.getText().toString();
        int nota = Integer.parseInt(value);
        String value2 = editBodega.getText().toString();
        long bodega = Long.parseLong(value2);
        String value3 = editDenominacio.getText().toString();
        long denominacio = Long.parseLong(value3);
        String value4 = editNota.getText().toString();
        float preu = Float.parseFloat(value4);

        Vi vino = new Vi();

        vino.setNomVi(editNom.getText().toString());
        vino.setAnada(editAnada.getText().toString());
        vino.setLloc(editLloc.getText().toString());
        vino.setTipus(editTipus.getText().toString());
        vino.setGraduacio(editGraduacio.getText().toString());
        vino.setData( editData.getText().toString());
        vino.setComentari(editComentari.getText().toString());
        vino.setIdBodega(bodega);
        vino.setIdDenominacio(denominacio);
        vino.setPreu(preu);
        vino.setValOlfativa(editValOlfa.getText().toString());
        vino.setValGustativa(editValGusta.getText().toString());
        vino.setValVisual(editValVisual.getText().toString());
        vino.setNota(nota);
        vino.setFoto(editFoto.getText().toString());

        //Obrim la base de dades
        bd = new DataSourceVi(this);
        try {
            bd.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        bd.createVi(vino);
        bd.close();
        finish();
    }

    public void ActualizarVino(){
        String value= editNota.getText().toString();
        int nota = Integer.parseInt(value);
        String value2 = editBodega.getText().toString();
        long bodega = Long.parseLong(value2);
        String value3 = editDenominacio.getText().toString();
        long denominacio = Long.parseLong(value3);
        String value4 = editNota.getText().toString();
        float preu = Float.parseFloat(value4);
        String valueID  = ID.getText().toString();
        long id = Long.parseLong(valueID);

        Vi vino = new Vi();
        vino.setId(id);
        vino.setNomVi(editNom.getText().toString());
        vino.setAnada(editAnada.getText().toString());
        vino.setLloc(editLloc.getText().toString());
        vino.setTipus(editTipus.getText().toString());
        vino.setGraduacio(editGraduacio.getText().toString());
        vino.setData( editData.getText().toString());
        vino.setComentari(editComentari.getText().toString());
        vino.setIdBodega(bodega);
        vino.setIdDenominacio(denominacio);
        vino.setPreu(preu);
        vino.setValOlfativa(editValOlfa.getText().toString());
        vino.setValGustativa(editValGusta.getText().toString());
        vino.setValVisual(editValVisual.getText().toString());
        vino.setNota(nota);
        vino.setFoto(editFoto.getText().toString());

        //Obtenim la BD
        bd = new DataSourceVi(this);
        try {
            bd.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //Crida a la BD
        bd.updateVi(vino);
        bd.close();
        finish();
    }

    public void DeleteVino(){
        String valueID  = ID.getText().toString();
        long id = Long.parseLong(valueID);

        Vi vino = new Vi();
        vino.setId(id);

        bd = new DataSourceVi(this);
        try {
            bd.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //Crida a la BD
        bd.deleteVi(vino);
        bd.close();
        finish();
    }

    public void RellenarVino(Vi vi){
        editNom.setText(vi.getNomVi());
    }
}

