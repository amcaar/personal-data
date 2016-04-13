package letra.dni;

/**
 * Realiza verificaciones sobre distintos datos personales:
 * <ul>
 * <li>Averigua la letra del DNI introduciendole los digitos.</li>
 * <li>Comprueba si una tarjeta de credito existe.</li>
 * </ul>
 * 
 * @author Amanda Calatrava Arroyo
 * @author Cristina Yenyxe Gonzalez Garcia
 */
public class Validador {

	private static char letras[] = { 'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F',
			'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H', 'L', 'C',
			'K', 'E' };

	/**
	 * Calcula la letra de un DNI a partir de su parte numerica mediante el
	 * algoritmo del 23. En el caso de DNI para extranjeros, el digito inicial
	 * se traduce de la siguiente forma: X = 0, Y = 1, Z = 2.
	 * 
	 * @param dni
	 *            La cadena numerica del DNI
	 * @throws NumberFormatException
	 *             Cuando se introduce una cadena cuyo formato no corresponde
	 *             con el de un DNI
	 * @return La letra
	 */
	public static char letraDni(String dni) throws NumberFormatException {
		switch (dni.charAt(0)) {
		case 'X':
			dni = dni.replaceFirst("X", "0");
			break;
		case 'Y':
			dni = dni.replaceFirst("Y", "1");
			break;
		case 'Z':
			dni = dni.replaceFirst("Z", "2");
			break;
		}

		try {
			int resto = Integer.parseInt(dni) % 23;
			return letras[resto];
		} catch (NumberFormatException e) {
			throw new NumberFormatException("El DNI no debe contener letras");
		}
	}

	/**
	 * <p>
	 * Comprueba que el numero indicado es de una tarjeta de credito valida.
	 * Para ello, se toman los caracteres por parejas, el primero se multiplica
	 * por dos y se le suma el segundo. En caso de que la multiplicacion por dos
	 * resulte mayor o igual que 10, se suman las dos cifras que componen este
	 * resultado.
	 * </p>
	 * <p>
	 * Una vez hecha la suma, si el modulo entre 10 es cero significa que la
	 * tarjeta existe, y si no que es falsa.
	 * </p>
	 * 
	 * @param tarjeta
	 *            El numero de tarjeta a consultar
	 * @throws NumberFormatException
	 *             Cuando se introduce una cadena que no corresponde con un
	 *             numero de tarjeta de credito
	 * @return Si la tarjeta existe
	 */
	public static boolean comprobarTarjetaCredito(String tarjeta)
			throws NumberFormatException {
		int valor = 0;
		if (tarjeta.length() != 16)
			throw new NumberFormatException(
					"Longitud de numero de tarjeta no valido");
		try {
			Long.parseLong(tarjeta);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			throw new NumberFormatException("Numero de tarjeta no valido");
		}
		for (int i = 0; i < tarjeta.length() - 1; i += 2) {
			int dup = Character.getNumericValue(tarjeta.charAt(i)) * 2;
			if (dup >= 10) {
				String aux = String.valueOf(dup);
				dup = Character.getNumericValue(aux.charAt(0))
						+ Character.getNumericValue(aux.charAt(1));
			}
			valor += dup + Character.getNumericValue(tarjeta.charAt(i + 1));
		}
		return valor % 10 == 0;
	}

}