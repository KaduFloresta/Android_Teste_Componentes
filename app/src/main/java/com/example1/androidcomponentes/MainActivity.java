package com.example1.androidcomponentes;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {
    // Criando atributos para acesso dos métodos...
    private TextInputEditText campoNome;
    private TextInputEditText campoEmail;
    private CheckBox checkEmail, checkSms, checkWhats;
    private RadioButton sexoMasculino, sexoFeminino;
    private Switch switchSenha;
    private ToggleButton toggleSenha;
    private ProgressBar progressBarHorizontal, progressBarCircular;
    private int progresso = 0;
    private SeekBar seekBarEscala;
    private TextView textProgresso, textoResultado;

    @Override // Método chamado antes da interface carregar...
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Configuração de interface...
        setContentView(R.layout.activity_main);

        // Criando objetos acessiveis para todos os métodos...
        // ...carrega antes de exibir na tela.

        // Caixas de Texto
        campoNome = findViewById(R.id.editNome);
        campoEmail = findViewById(R.id.editEmail);

        // Checkbox
        checkEmail = findViewById(R.id.checkEmail);
        checkSms = findViewById(R.id.checkSms);
        checkWhats = findViewById(R.id.checkWhats);

        // RadioButton
        sexoMasculino = findViewById(R.id.radioBtnMasculino);
        sexoFeminino = findViewById(R.id.radioBtnFeminino);

        // Switch
        switchSenha = findViewById(R.id.switchSenha);

        // ToggleButton
        toggleSenha = findViewById(R.id.toggleSenha);

        // ProgressBar
        textProgresso = findViewById(R.id.textProgresso);
        progressBarHorizontal = findViewById(R.id.progressBarHorizontal);
        progressBarCircular = findViewById(R.id.progressBarCircular);

        // Ocultado o progressBar enquanto ele não é usado
        textProgresso.setVisibility(View.GONE);
        progressBarHorizontal.setVisibility(View.GONE);
        progressBarCircular.setVisibility(View.GONE);

        seekBarEscala = findViewById(R.id.seekBarEscala);


        // TextView
        textoResultado = findViewById(R.id.textResultado);
    }

    // Botão enviar as Strings coletadas para o TextView "Resultado"
    public void enviar(View view) {
        // Recupera o que foi digitado nas caixas de texto...
        // ...conversão para Strint.

        // TEXT INPUT LAYOUT
        String nome = campoNome.getText().toString();
        String email = campoEmail.getText().toString();

        // CHECKBOX
        // Verifica qual checkbox foi selecionado...
        String resultadoNews = "";
        if (checkEmail.isChecked()) {
            String corSelecionada = checkEmail.getText().toString();
            resultadoNews = corSelecionada;
        }
        // ...para não sobrescrever a opções qdo há mais de 1 check selecionado.
        if (checkSms.isChecked()) {
            String corSelecionada = checkSms.getText().toString();
            resultadoNews = resultadoNews + corSelecionada;
        }
        if (checkWhats.isChecked()) {
            String corSelecionada = checkWhats.getText().toString();
            resultadoNews = resultadoNews + corSelecionada;
        }

        // RADIO BUTTON
        // Como apenas uma das opções podem ser selecionadas...
        // ... o IF/ELSE é feito dessa maneira.
        String resultadoSexo = "";
        if (sexoMasculino.isChecked()) {
            String generoSelecionado = sexoMasculino.getText().toString();
            resultadoSexo = generoSelecionado;
        } else if (sexoFeminino.isChecked()) {
            String generoSelecionado = sexoFeminino.getText().toString();
            resultadoSexo = generoSelecionado;
        }

        // SWITCH BUTTON
        String resultadoSenha = "";
        if (switchSenha.isChecked()) {
            resultadoSenha = "Ligado";
        } else {
            resultadoSenha = "Desligado";
        }

        // TOGGLE BUTTON
        String resultadoToggle = "";
        if (toggleSenha.isChecked()) {
            resultadoToggle = "Ligado";
        } else {
            resultadoToggle = "Desligado";
        }

        // SEEK BAR
        seekBarEscala.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override // Quando clica e arrasta o elemento
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            }

            @Override // Quando clica em cima do elemento
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override // Quando ele solta o elemento
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        // TextView recebe todos os resultados concatenados...
        textoResultado.setText(
                "Nome: " + nome + "\n" +
                "E-mail: " + email + "\n" +
                "Newsletter: " + resultadoNews + "\n" +
                "Gênero: " + resultadoSexo + "\n" +
                "Lembrar Senha: " + resultadoSenha + "\n" +
                "Toggle: " + resultadoToggle + "\n" +
                "SeekBar Progresso: " + seekBarEscala.getProgress()
        );

        // Traz de volta o progressBar no evento do botão
        textProgresso.setVisibility(View.VISIBLE);
        progressBarHorizontal.setVisibility(View.VISIBLE);
        progressBarCircular.setVisibility(View.VISIBLE);
        this.progresso = this.progresso + 1;

        progressBarHorizontal.setProgress(this.progresso);

//        if (this.progresso == 1) {
//            progressBarCircular.setVisibility(View.GONE);
//        }
        abrirToast("Ação realizada com sucesso!");
    }

    // Botão limpar as Strings vazias para o TextView "Resultado"
    public void limpar(View view) {
        abrirDiolog("");
    }

    // Criação do Toast (Mensagem)
    private void abrirToast(String s) {
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
    }

    // Criação do AlertDiolog (caixa de confirmação)
    private void abrirDiolog(String s) {
        // Instanciar
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);

        // Configurar titulo
        dialog.setTitle("Mensagem de Alerta");
        dialog.setMessage("Deseja realmente limpar os componentesAntonio Maria?");

        // Configurar cancelamento
        dialog.setCancelable(true); // TRUE = clicando fora do alert, ele fecha

        // Configurar icone
        dialog.setIcon(android.R.drawable.ic_dialog_alert);

        // Configurar ações SIM e NÃO
        dialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // Ações para limpar as caixas e "remover seleções...
                campoNome.setText("");
                campoEmail.setText("");
                checkEmail.setChecked(false);
                checkSms.setChecked(false);
                checkWhats.setChecked(false);
                sexoMasculino.setChecked(false);
                sexoFeminino.setChecked(false);
                switchSenha.setChecked(false);
                toggleSenha.setChecked(false);
                textoResultado.setText("");
                seekBarEscala.setProgress(0);

                // Ocultado o progressBar no evento do botão
                textProgresso.setVisibility(View.GONE);
                progressBarHorizontal.setVisibility(View.GONE);
                progressBarCircular.setVisibility(View.GONE);

                Toast.makeText(
                        getApplicationContext(),
                        "O formulário foi limpo!",
                        Toast.LENGTH_SHORT
                ).show();
            }
        });

        dialog.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });

        dialog.create();
        dialog.show();
    }
}