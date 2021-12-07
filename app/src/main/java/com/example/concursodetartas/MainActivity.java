package com.example.concursodetartas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    //Aquí me creo las diferentes variables que voy a necesitar
    EditText nombre;
    EditText apellidos;
    EditText edad;
    RadioButton hombre;
    RadioButton mujer;
    CheckBox participar;
    EditText cual;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        comprobarCheckBox();
    }






    //Con el booleano logro que al declararlo false, si alguna de esas condiciones ocurre, siga false
    //De lo contrario, nos devolvera un true estando todo correcto
    public Boolean checkInputs() {
        Boolean todoOk = false;
        nombre = findViewById(R.id.nombre);
        apellidos = findViewById(R.id.apellidos);
        edad = findViewById(R.id.edad);
        hombre = findViewById(R.id.hombre);
        mujer = findViewById(R.id.mujer);
        cual = findViewById(R.id.cual);
        participar = findViewById(R.id.participar);

        String name = nombre.getText().toString();
        String surname = apellidos.getText().toString();
        String age = edad.getText().toString();
        String which = cual.getText().toString();

        // En esta funcion compruebo que nombre, apellidos y edad no esten vacíos.
        if (name.isEmpty() || surname.isEmpty() || age.isEmpty()) {
            Toast.makeText(this, "Debe rellenar todos los campos", Toast.LENGTH_SHORT).show();
            todoOk = false;
        }
        //Tambien compruebo con el if que al poner menos de 18 años muestre el mensaje que debe ser mayor de edad
        else if (Integer.parseInt(age) <= 17) {
            Toast.makeText(this, "Debes de ser mayor de edad", Toast.LENGTH_SHORT).show();
            todoOk = false;
        }
        //Chequeamos si ha marcado el genero hombre o mujer
        else if (!hombre.isChecked() && !mujer.isChecked()) {
            Toast.makeText(this, "Debes de elegir un genero", Toast.LENGTH_SHORT).show();
            todoOk = false;
        } //Comprobamos que si ha marcado el check, diga en que otro concurso ha estado
        else if (participar.isChecked()) {
            if (which.isEmpty()) {
                Toast.makeText(this, "Debes de introducir un concurso", Toast.LENGTH_SHORT).show();
                todoOk = false;
            } else {
                todoOk = true;
            }
        } else {
            todoOk = true;
        }

        return todoOk;
    }

    //Con esta funcion compruebo que el booleano me ha devuelto true de la funcion chekInputs y muestra el mensaje correcto
    public void inscribir(View view) {
        if (checkInputs()) {
            Toast.makeText(this, "Inscrito correctamente", Toast.LENGTH_SHORT).show();
        }
    }

    //En esta funcion comprobamos si ha marcado el check de participar en otro concurso o no
    //Si es que si, hacemos visible el text view de cual
    //Si es que no desaparece
    public void comprobarCheckBox() {
        participar = findViewById(R.id.participar);
        cual = findViewById(R.id.cual);
        participar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (participar.isChecked()) {
                    cual.setVisibility(View.VISIBLE);
                } else {
                    cual.setVisibility(View.GONE);
                }
            }
        });
    }


}