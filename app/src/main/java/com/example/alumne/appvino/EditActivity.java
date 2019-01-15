package com.example.alumne.appvino;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.SQLException;

public class EditActivity extends Activity implements View.OnClickListener {
    Button buttonAfegir, buttonActualitzar;
    EditText editID, editNom, editAnada, editTipus, editLloc, editGraduacio, editData, editComentari, editBodega, editDenominacio, editPreu, editValOlfa, editValGusta, editValVisual, editNota, editFoto;
    DataSourceVi bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

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
    }

    @Override
    public void onClick(View v) {
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

        if (v == buttonAfegir){
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

        else if (v == buttonActualitzar){
            long id;

            //Obtenim la BD
            bd = new DataSourceVi(this);
            try {
                bd.open();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            //Crida a la BD
            boolean result = bd.updateVi(vino);

            //Comprovem el resultat, si s’ha pogut actualitzar la BD o no
            if (result)
                Toast.makeText(this, "Element modificat", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(this, "No s’ha pogut modificar l’element", Toast.LENGTH_SHORT).show();

            //Tanquem la BD
            bd. close();

            //Tanca l’activitat.
            finish();

        }
    }
}

