package letra.dni;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

/**
 * Clase principal que extiende la funcionalidad de Activity, actuando como 
 * actividad principal de la aplicacion.
 * 
 * @author Amanda Calatrava Arroyo
 * @author Cristina Yenyxe Gonzalez Garcia
 */
public class Principal extends Activity {

	private ImageButton botonSalida;

	private Button okDni;
	private Button okTarjeta;

	private EditText campoDni;
	private EditText campoTarjeta;

	/**
	 * Crea el boton para salir y los formularios para introducir los 
	 * datos sobre DNI y sobre el numero de tarjeta.
	 */
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		// Controlador de eventos del boton para salir de la aplicacion
		botonSalida = (ImageButton) findViewById(R.id.Exit);
		botonSalida.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				// Salir de la aplicacion
				alertaSalir();
			}
		});

		// Controlador de eventos del boton para calcular la letra del DNI
		okDni = (Button) findViewById(R.id.okDni);
		campoDni = (EditText) findViewById(R.id.dni);
		okDni.setOnClickListener(new CampoOnClickListener(campoDni, this));

		// Controlador de eventos del boton para comprobar un numero de tarjeta
		okTarjeta = (Button) findViewById(R.id.okTarjeta);
		campoTarjeta = (EditText) findViewById(R.id.tarjeta);
		okTarjeta.setOnClickListener(new CampoOnClickListener(campoTarjeta,
				this));
	}

	/**
	 * Crea un aviso grafico y lo muestra por pantalla.
	 * 
	 * @param mensaje
	 *            Cadena de texto que se desea mostrar al usuario
	 * @param milisegundos
	 *            Tiempo que permanecera en pantalla el mensaje
	 */
	public void avisoGrafico(String mensaje, int milisegundos) {
		// Creamos el dialogo
		Toast dialog = Toast.makeText(getApplicationContext(), mensaje,
				Toast.LENGTH_LONG);
		// Lo colocamos en pantalla
		dialog.setGravity(Gravity.CENTER, 0, 0);
		dialog.setDuration(milisegundos);
		dialog.show();
	}

	/**
	 * Muestra un aviso al usuario para preguntarle si desea abandonar
	 * o no la aplicacion.
	 */
	private void alertaSalir() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("¿Seguro que deseas salir?").setCancelable(false)
				.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						Principal.this.finish();
					}
				})
				.setNegativeButton("No", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						dialog.cancel();
					}
				});
		AlertDialog alert = builder.create();
		alert.show();
	}
}
