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
    EditText editNom, editAnada, editTipus, editLloc, editGraduacio, editData, editComentari, editBodega, editDenominacio, editPreu, editValOlfa, editValGusta, editValVisual, editNota;
    DataSourceVi bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        editTextNomVi = (EditText) findViewById(R.id.editTextNomVi);
        editTextData = (EditText) findViewById(R.id.editTextData);
        editTextTipus = (EditText) findViewById(R.id.editTextTipus);

        buttonAfegir = (Button) findViewById(R.id.buttonAfegir);
        buttonAfegir.setOnClickListener(this);
        buttonActualitzar = (Button) findViewById(R.id.buttonActualitzar);
        buttonActualitzar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == buttonAfegir){
            //Obrim la base de dades
            bd = new DataSourceVi(this);
            try {
                bd.open();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            //Inserim el contacte

            if (bd.createVi(editTextNomVi.getText().toString(), editTextData.getText().toString(), editTextTipus.getText().toString()) != -1) {
                Toast.makeText(this, "Afegit correctament", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Error a l’afegir", Toast.LENGTH_SHORT).show();
            }
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

            //Identificador de la caixa de text
            id = Long.parseLong(editID.getText().toString());

            //Crida a la BD
            boolean result = bd.updateVi(id, editNom.getText().toString(), editEmail.getText().toString());

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

