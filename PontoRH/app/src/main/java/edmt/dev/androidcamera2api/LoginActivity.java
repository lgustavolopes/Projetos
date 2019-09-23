package edmt.dev.androidcamera2api;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    private EditText Empresa;
    private EditText Matricula;
    private EditText Senha;
    private TextView Info;
    private Button Login;
    private int counter =5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Empresa = (EditText) findViewById(R.id.txtEmpresa);
        Matricula = (EditText) findViewById(R.id.txtMatricula);
        Senha = (EditText) findViewById(R.id.txtSenha);
        Info = (TextView) findViewById(R.id.txtMessage);
        Login = (Button) findViewById(R.id.btnEntrar);

        Info.setText("Você tem 5 tentativas");

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                validate(Empresa.getText().toString(), Matricula.getText().toString(), Senha.getText().toString());

            }
        });
    }


    private void validate(String pEmpresa, String pMatricula, String pSenha){

        try {

        //Realiza o calculo da senha
        // Formula: 10000 - ((Empresa * 3) + (Dig1 * 1) + (Dig2 * 3) + (Dig3 * 1) + (Dig4 * 3) + (Dig5 * 1) + (Dig6 * 3) + (Dig7 * 1))

        int TotalDigitos = pMatricula.length();
        String SenhaCorreta;

        Integer Dig1 = 0;
        Integer Dig2 = 0;
        Integer Dig3 = 0;
        Integer Dig4 = 0;
        Integer Dig5 = 0;
        Integer Dig6 = 0;
        Integer Dig7 = 0;


       for(int i=0; i < pMatricula.length(); i++){

            char pegaCharDaString;
            String converteCharParaString;


            pegaCharDaString = pMatricula.charAt(i);

            converteCharParaString = String.valueOf(pegaCharDaString);

            if (i==0) {
                Dig1 = Integer.valueOf(converteCharParaString) * 1;
            }

           if (i==1) {
               Dig2 = Integer.valueOf(converteCharParaString) * 3;
           }

           if (i==2) {
               Dig3 = Integer.valueOf(converteCharParaString) * 1;
           }

           if (i==3) {
               Dig4 = Integer.valueOf(converteCharParaString) * 3;
           }

           if (i==4) {
               Dig5 = Integer.valueOf(converteCharParaString) * 1;
           }

           if (i==5) {
               Dig6 = Integer.valueOf(converteCharParaString) * 3;
           }

           if (i==6) {
               Dig7 = Integer.valueOf(converteCharParaString) * 1;
           }

        }

       SenhaCorreta = String.valueOf(10000-((Integer.valueOf(pEmpresa)*3)+(Dig1+Dig2+Dig3+Dig4+Dig5+Dig6+Dig7)));

       if(pSenha.equals(SenhaCorreta)){
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }else{

            Info.setText("Login ou senha inválidos, tente novamente.");
        }

        } catch (Throwable e) {
            Info.setText("Login ou senha inválidos, tente novamente.");
        }

    }
}
