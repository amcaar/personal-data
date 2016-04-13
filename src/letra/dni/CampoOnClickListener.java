package letra.dni;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import letra.dni.Validador;

/**
 * Implementa la interfaz OnClickListener, haciendo de listener para el boton
 * asociado a un campo de texto. De esta forma, cuando se presiona dicho boton
 * se obtiene el contenido del campo y se realiza una accion asociada a el.
 * 
 * @author Amanda Calatrava Arroyo
 * @author Cristina Yenyxe Gonzalez Garcia
 */

public class CampoOnClickListener implements OnClickListener {

	private EditText campo;

	private Principal principal;

	/**
	 * Construye un listener para un campo de texto
	 * 
	 * @param newCampo
	 *            El campo que contiene la informacion sobre la que se desea
	 *            realizar el calculo
	 * @param p
	 *            Objeto que extiende de Activity, sobre el que presentar los
	 *            resultados
	 */
	public CampoOnClickListener(EditText newCampo, Principal p) {
		this.campo = newCampo;
		this.principal = p;
	}

	// ////////////////////////////////////////////////////////////////////////
	// /// Metodo necesario que implementa la interfaz OnClickListener ////
	// ////////////////////////////////////////////////////////////////////////

	/**
	 * Cuando se presiona el boton asociado a un campo, se obtiene el valor de
	 * este y se realiza una accion sobre el:
	 * 
	 */
	public void onClick(View view) {
		String valorCampo = campo.getText().toString();
		if (campo.getId() == R.id.dni) {
			calcularLetraDni(valorCampo);
		} else {
			if (campo.getId() == R.id.tarjeta) {
				calcularValidezTarjeta(valorCampo);
			}
		}

	}

	/**
	 * Metodo que deriva su funcionamiento en letraDni de la clase
	 * CalculoDatosPersonales
	 * 
	 * @param dni
	 *            Cadena de texto contenida en el campo del numero del DNI
	 */
	private void calcularLetraDni(String dni) {
		String res = null;
		try {
			char letra = Validador.letraDni(dni);
			res = "La letra del DNI introducido es " + letra;
		} catch (NumberFormatException e) {
			res = "El DNI introducido no es válido";
		}
		principal.avisoGrafico(res, 4000);
	}

	/**
	 * Metodo que deriva su funcionamiento en comprobarTarjetaCredito de la
	 * clase CalculoDatosPersonales
	 * 
	 * @param tarjeta
	 *            Cadena de texto contenida en el campo del numero de la tarjeta
	 *            de credito
	 */
	private void calcularValidezTarjeta(String tarjeta) {
		String res = "";
		boolean valido;
		try {
			valido = Validador.comprobarTarjetaCredito(tarjeta);
		} catch (NumberFormatException e) {
			valido = false;
		}
		if (valido) {
			res = "El número de tarjeta introducido es válido";
		} else {
			res = "El número de tarjeta introducido no es válido";
		}
		principal.avisoGrafico(res, 4000);
	}

}
