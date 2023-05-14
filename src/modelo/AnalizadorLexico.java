/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad del Quindio (Armenia - Colombia)
 * Programa de Ingenieria de Sistemas y Computacion
 * Abril 2023
 * Asignatura: Teoria de Lenguajes Formales
 * Fase 2 Proyecto Final: AnalizadorLexico
 * Autores: Juan Sebastian Corredor Cabrera y Frank Soto Paz
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package modelo;

import java.util.ArrayList;

/**
 * Clase que modela un analizador lexico
 */

public class AnalizadorLexico {

	// -----------------------------------------------------------------
	// Metodos
	// -----------------------------------------------------------------

	/**
	 * Extrae los tokens de un codigo fuente dado
	 * 
	 * @param cod - codigo al cual se le van a extraer los tokens - !=null
	 * @return vector con los tokens
	 */
	public ArrayList extraerTokens(String cod) {
		Token token;
		ArrayList vectorTokens = new ArrayList();

		// El primer token se extrae a partir de posicion cero
		int i = 0;

		// Ciclo para extraer todos los tokens
		while (i < cod.length()) {
			// Extrae el token de la posicion i
			token = extraerSiguienteToken(cod, i);
			vectorTokens.add(token);
			i = token.darIndiceSiguiente();
		}
		return vectorTokens;
	}

	/**
	 * Extrae el token de la cadena cod a partir de la posicion i, basandose en el
	 * Automata
	 * 
	 * @param cod - codigo al cual se le va a extraer un token - codigo!=null
	 * @param i   - posicion a partir de la cual se va a extraer el token - i>=0
	 * @return token que se extrajo de la cadena
	 */
	public Token extraerSiguienteToken(String cod, int i) {
		Token token;

		// Intenta extraer un real
		token = extraerValorAsignacionReal(cod, i);
		if (token != null)
			return token;

		// Intenta extraer la palabra para los reales
		token = extraerReales(cod, i);
		if (token != null)
			return token;

		// Intenta extraer un separador de sentencia
		token = extraerSeparadorSentencia(cod, i);
		if (token != null)
			return token;

		// INTENTA EXTRAER UNA PALABRA RESERVADA PARA CADENA DE CARACTERES
		token = extraerPalabraReservadaParaCadenaDeCaracteres(cod, i);
		if (token != null)
			return token;

		// Intenta extraer Palabra reservada constante
		token = extraerPalabraReservadaCons(cod, i);
		if (token != null)
			return token;

		// INTENTA EXTRAER UN IDENTIFICADOR DE METODO
		token = extraerIdentificadorDeMetodo(cod, i);
		if (token != null)
			return token;

		// Intenta extraer un Identificador de variable
		token = extraerIdentificadorVariable(cod, i);
		if (token != null)
			return token;

		// Intenta extraer un entero
		token = extraerEntero(cod, i);
		if (token != null)
			return token;

		// Intenta extraer un operador aditivo
		token = extraerOperadorAditivo(cod, i);
		if (token != null)
			return token;

		// Intenta extraer un operador relacional
		token = extraerOperadorRelacional(cod, i);
		if (token != null)
			return token;

		// Intenta extraer palabra enteros
		token = extraerPalabraEnteros(cod, i);
		if (token != null)
			return token;

		// Intenta extraer un operador de asignacion
		token = extraerOperadorAsignacion(cod, i);
		if (token != null)
			return token;

		// Intenta extraer un operador logico
		token = extraerOperadorLogico(cod, i);
		if (token != null)
			return token;

		// Intenta extraer un operador terminal .
		token = extraerOperadorTerminalInicial(cod, i);
		if (token != null)
			return token;

		// Intenta extraer palabra clase
		token = extraerPalabraClase(cod, i);
		if (token != null)
			return token;

		// Intenta extraer un identificador
		token = extraerIdentificador(cod, i);
		if (token != null)
			return token;

		// INTENTA EXTRAER UN OPERADOR ARITMETICO
		token = extraerOperadorAritmetico(cod, i);
		if (token != null)
			return token;

		// INTENTA EXTRAER UN SIMBOLO DE ABRIR
		token = extraerSimboloDeAbrir(cod, i);
		if (token != null)
			return token;

		// INTENTA EXTRAER UNA PALABRA RESERVADA PARA BUCLE
		token = extraerPalabraReservadaParaBucle(cod, i);
		if (token != null)
			return token;

		// Intenta extraer simbolos de cerrar
		token = extraerOperadorCerrar(cod, i);
		if (token != null)
			return token;

		// Intenta extraer palabras reservada decision
		token = extraerPalabraReservadaDecision(cod, i);
		if (token != null)
			return token;

		// Intenta extraer identificador de clase
		token = extraerIdentificadorClase(cod, i);
		if (token != null)
			return token;

		// Intenta extraer palabras reservadas ,tipo de dato caracteres
		token = extraerPalabraCaracter(cod, i);
		if (token != null)
			return token;

		// Intenta extraer valor asignacion caracter
		token = extraerAsignacionCaracter(cod, i);
		if (token != null)
			return token;

		// Intenta extraer valor cadena de caracteres.
		token = extraerValorCadenaCaracteres(cod, i);
		if (token != null)
			return token;

		// Extrae un token no reconocido
		token = extraerNoReconocido(cod, i);
		return token;

		// CATEGORIA OPERADORES
	}

	/**
	 * Intenta extraer un operador aritmetico( ( S U M U R E S T) U ( M U L T U D I
	 * V) U (S U M E U M O D) )
	 * 
	 * @param cod - codigo al cual se le va a intentar extraer el operador
	 *            aritmetico - codigo!=null
	 * @param i   - posicion a partir de la cual se va a intentar extraer el
	 *            operador aritmetico - 0<=i<codigo.length()
	 * @return el token operador aritmetico o NULL, si el token en la posicion dada
	 *         no es un operador aritmetico.El Token se compone de el lexema, el
	 *         tipo y la posicion del siguiente lexema.
	 */

	public Token extraerOperadorAritmetico(String cod, int i) {
		// TODO Auto-generated method stub
		int aux = i;
		String lex;
		// SUME
		if (cod.charAt(i) == 'S') {
			int j = i + 1;
			if (j < cod.length() && cod.charAt(j) == 'U') {
				j++;
				if (j < cod.length() && cod.charAt(j) == 'M') {
					j++;
					if (j < cod.length() && cod.charAt(j) == 'E') {
						j++;
						lex = cod.substring(i, j);
						Token token = new Token(lex, Token.OPERADOR_ARITMETICO, j);
						return token;
					}
					lex = cod.substring(i, j);
					Token token = new Token(lex, Token.OPERADOR_ARITMETICO, j);
					return token;
				}
			}
		}

		// DIV
		i = aux;
		if (cod.charAt(i) == 'D') {
			int j = i + 1;
			if (j < cod.length() && cod.charAt(j) == 'I') {
				j++;
				if (j < cod.length() && cod.charAt(j) == 'V') {
					j++;

					lex = cod.substring(i, j);
					Token token = new Token(lex, Token.OPERADOR_ARITMETICO, j);
					return token;

				}
			}

		}
		// MOD
		i = aux;
		if (cod.charAt(i) == 'M') {
			int j = i + 1;
			if (j < cod.length() && cod.charAt(j) == 'O') {
				j++;
				if (j < cod.length() && cod.charAt(j) == 'D') {
					j++;

					lex = cod.substring(i, j);
					Token token = new Token(lex, Token.OPERADOR_ARITMETICO, j);
					return token;

				}
			}

		}
		// SUM
		i = aux;
		if (cod.charAt(i) == 'S') {
			int j = i + 1;
			if (j < cod.length() && cod.charAt(j) == 'U') {
				j++;
				if (j < cod.length() && cod.charAt(j) == 'M') {
					j++;

					lex = cod.substring(i, j);
					Token token = new Token(lex, Token.OPERADOR_ARITMETICO, j);
					return token;

				}
			}

		}
		// MULT
		i = aux;
		if (cod.charAt(i) == 'M') {
			int j = i + 1;
			if (j < cod.length() && cod.charAt(j) == 'U') {
				j++;
				if (j < cod.length() && cod.charAt(j) == 'L') {
					j++;
					if (j < cod.length() && cod.charAt(j) == 'T') {
						j++;
						lex = cod.substring(i, j);
						Token token = new Token(lex, Token.OPERADOR_ARITMETICO, j);
						return token;
					}
				}
			}
		}
		// REST
		i = aux;
		if (cod.charAt(i) == 'R') {
			int j = i + 1;
			if (j < cod.length() && cod.charAt(j) == 'E') {
				j++;
				if (j < cod.length() && cod.charAt(j) == 'S') {
					j++;
					if (j < cod.length() && cod.charAt(j) == 'T') {
						j++;
						lex = cod.substring(i, j);
						Token token = new Token(lex, Token.OPERADOR_ARITMETICO, j);
						return token;
					}
				}
			}
		}
		return null;
	}

	/**
	 * Intenta extraer un operador relacional (S U M).(W U E) U (E.E) U (N.E.G.E)
	 * 
	 * @param cod - codigo al cual se le va a intentar extraer el operador
	 *            relacional - codigo!=null
	 * @param i   - posicion a partir de la cual se va a intentar extraer el
	 *            operador relacional - 0<=i<codigo.length()
	 * @return el token operador relacional o NULL, si el token en la posicion dada
	 *         no es un operador relacional.El Token se compone de el lexema, el
	 *         tipo y la posicion del siguiente lexema.
	 */

	public Token extraerOperadorRelacional(String cod, int i) {

		int j;
		String lex;
		if (cod.charAt(i) == 'S') {
			j = i + 1;
			if (j < cod.length() && cod.charAt(j) == 'W') {
				j++;
				lex = cod.substring(i, j);
				Token token = new Token(lex, Token.OPERADOR_RELACIONAL, j);
				return token;
			}
		}

		if (cod.charAt(i) == 'M') {
			j = i + 1;
			if (j < cod.length() && cod.charAt(j) == 'W') {
				j++;
				lex = cod.substring(i, j);
				Token token = new Token(lex, Token.OPERADOR_RELACIONAL, j);
				return token;
			}
		}

		if (cod.charAt(i) == 'S') {
			j = i + 1;
			if (j < cod.length() && cod.charAt(j) == 'E') {
				j++;
				lex = cod.substring(i, j);
				Token token = new Token(lex, Token.OPERADOR_RELACIONAL, j);
				return token;
			}
		}

		if (cod.charAt(i) == 'M') {
			j = i + 1;
			if (j < cod.length() && cod.charAt(j) == 'E') {
				j++;
				lex = cod.substring(i, j);
				Token token = new Token(lex, Token.OPERADOR_RELACIONAL, j);
				return token;
			}
		}

		if (cod.charAt(i) == 'E') {
			j = i + 1;
			if (j < cod.length() && cod.charAt(j) == 'E') {
				j++;
				lex = cod.substring(i, j);
				Token token = new Token(lex, Token.OPERADOR_RELACIONAL, j);
				return token;
			}
		}

		if (cod.charAt(i) == 'N') {
			j = i + 1;

			if (j < cod.length() && cod.charAt(j) == 'E') {
				j++;

				if (j < cod.length() && cod.charAt(j) == 'G') {
					j++;

					if (j < cod.length() && cod.charAt(j) == 'E') {
						j++;

						lex = cod.substring(i, j);
						Token token = new Token(lex, Token.OPERADOR_RELACIONAL, j);
						return token;
					}

				}
			}
		}

		return null;

	}

	/**
	 * Intenta extraer un operador logico de la cadena cod a partir de la
	 * 
	 * posicion i, basandose en el Automata
	 * 
	 * @param cod - codigo al cual se le va a intentar extraer el simbolo de abrir -
	 *            codigo!=null
	 * @param i   - posicion a partir de la cual se va a intentar extraer el simbolo
	 *            de abrir 0
	 */

	public Token extraerOperadorLogico(String cod, int i) {
		// TODO Auto-generated method stub

		// AND OR NEG
		String lex;
		if (cod.charAt(i) == 'A') {
			int j = i + 1;
			if (j < cod.length() && cod.charAt(j) == 'N') {
				j++;
				if (j < cod.length() && cod.charAt(j) == 'D') {
					j++;

					lex = cod.substring(i, j);
					Token token = new Token(lex, Token.OPERADOR_LOGICO, j);
					return token;

				}
			}
		}
		if (cod.charAt(i) == 'O') {
			int j = i + 1;
			if (j < cod.length() && cod.charAt(j) == 'R') {
				j++;

				lex = cod.substring(i, j);
				Token token = new Token(lex, Token.OPERADOR_LOGICO, j);
				return token;

			}
		}

		if (cod.charAt(i) == 'N') {
			int j = i + 1;
			if (j < cod.length() && cod.charAt(j) == 'E') {
				j++;
				if (j < cod.length() && cod.charAt(j) == 'G') {
					j++;

					lex = cod.substring(i, j);
					Token token = new Token(lex, Token.OPERADOR_LOGICO, j);
					return token;

				}
			}
		}
		return null;
	}

	/**
	 * Intenta extraer un operador de asignacion de la cadena cod a partir de la
	 * posicion i, basandose en el Automata
	 * 
	 * @param cod - codigo al cual se le va a intentar extraer el operador de
	 *            asignacion - codigo!=null
	 * @param i   - posicion a partir de la cual se va a intentar extraer el
	 *            operador de asingacion - 0<=i<codigo.length()
	 * @return el token operador asignacion o NULL, si el token en la posicion dada
	 *         no es un operador de asignacion. El Token se compone de el lexema, el
	 *         tipo y la posicion del siguiente lexema.
	 */
	public Token extraerOperadorAsignacion(String cod, int i) {
		if (cod.charAt(i) == 'E') {
			int j = i + 1;
			String lex = cod.substring(i, j);
			Token token = new Token(lex, Token.OPERADORASIGNACION, j);
			return token;
		}
		return null;
	}

	/**
	 * Intenta extraer un simbolo de abrir( ASCII 191 U ASCII 60.ASCII 60 ) U ASCCI
	 * 175 de la cadena cod a partir de la ┐ U < < U »
	 * 
	 * posicion i, basandose en el Automata
	 * 
	 * @param cod - codigo al cual se le va a intentar extraer el simbolo de abrir -
	 *            codigo!=null
	 * @param i   - posicion a partir de la cual se va a intentar extraer el simbolo
	 *            de abrir 0
	 */
	private Token extraerSimboloDeAbrir(String cod, int i) {
		// TODO Auto-generated method stub
		int aux = i;
		String lex;

		if (cod.charAt(i) == '┐') {
			int j = i + 1;

			lex = cod.substring(i, j);
			Token token = new Token(lex, Token.SIMBOLO_DE_ABRIR, j);
			return token;
		}

		i = aux;
		if (cod.charAt(i) == '<') {
			int j = i + 1;
			if (j < cod.length() && cod.charAt(j) == '<') {
				j++;

				lex = cod.substring(i, j);
				Token token = new Token(lex, Token.SIMBOLO_DE_ABRIR, j);
				return token;

			}

		}
		// MOD
		i = aux;
		if (cod.charAt(i) == '»') {
			int j = i + 1;

			lex = cod.substring(i, j);
			Token token = new Token(lex, Token.SIMBOLO_DE_ABRIR, j);
			return token;

		}
		return null;
	}

	/**
	 * Intenta extraer un operador de cerrar
	 * 
	 * @param cod - codigo al cual se le va a intentar extraer el operador de cerrar
	 *            - codigo!=null
	 * @param i   - posicion a partir de la cual se va a intentar extraer el
	 *            operador de cerrar - 0<=i<codigo.length()
	 * @return el token operador cerrar o NULL, si el token en la posicion dada no
	 *         es un operador cerrar.El Token se compone de el lexema, el tipo y la
	 *         posicion del siguiente lexema.
	 */

	public Token extraerOperadorCerrar(String cod, int i) {

		int j;
		String lex;
		if (cod.charAt(i) == '?') {
			j = i + 1;

			lex = cod.substring(i, j);
			Token token = new Token(lex, Token.SIMBOLO_CERRAR, j);
			return token;
		}

		if (cod.charAt(i) == 'w') {
			j = i + 1;

			lex = cod.substring(i, j);
			Token token = new Token(lex, Token.SIMBOLO_CERRAR, j);
			return token;
		}

		if (cod.charAt(i) == 'z') {
			j = i + 1;

			lex = cod.substring(i, j);
			Token token = new Token(lex, Token.SIMBOLO_CERRAR, j);
			return token;
		}

		return null;
	}

	/**
	 * Intenta extraer un terminal cod a partir de la
	 * 
	 * posicion i, basandose en el Automata
	 * 
	 * @param cod - codigo al cual se le va a intentar extraer el simbolo de abrir -
	 *            codigo!=null
	 * @param i   - posicion a partir de la cual se va a intentar extraer el simbolo
	 *            de abrir 0
	 */

	public Token extraerOperadorTerminalInicial(String cod, int i) {
		// TODO Auto-generated method stub

		// .

		int j;
		String lex;
		if (cod.charAt(i) == '.') {
			j = i + 1;

			lex = cod.substring(i, j);
			Token token = new Token(lex, Token.OPERADOR_TERMINAL_INICIAL, j);
			return token;
		}
		return null;
	}

	/**
	 * Intenta extraer un separador de sentecia (a)
	 * 
	 * @param cod - codigo al cual se le va a intentar extraer el separador de
	 *            sentencia- codigo!=null
	 * 
	 * @param i   - posicion a partir de la cual se va a intentar extraer el
	 *            operador aritmetico - 0<=i<codigo.length()
	 * @return el token separador de sentencia o NULL, si el token en la posicion
	 *         dada no es un separador de sentancia.El Token se compone de el
	 *         lexema, el tipo y la posicion del siguiente lexema.
	 */

	private Token extraerSeparadorSentencia(String cod, int i) {
		// TODO Auto-generated method stub

		if (cod.charAt(i) == 'a') {

			int j = i + 1;
			String lex = cod.substring(i, j);
			Token token = new Token(lex, Token.SEPARADOR_SENTENCIA, j);
			return token;
		}

		return null;
	}

	// CATEGORIA PALABRAS RESERVADAS DIFERENTES USOS

	/**
	 * Intenta extraer una palabra reservada bucle( D E S ) de la cadena cod a
	 * partir de la posicion i, basandose en el Automata
	 * 
	 * @param cod - codigo al cual se le va a intentar extraer la palabra reservada
	 *            para bucle de sentencia- codigo!=null
	 * 
	 * @param i   - posicion a partir de la cual se va a intentar extraer la palabra
	 *            reservada - 0<=i<codigo.length()
	 * @return el token palabra reservada para bucle o NULL, si el token en la
	 *         posicion dada no es un separador de sentancia.El Token se compone de
	 *         el lexema, el tipo y la posicion del siguiente lexema.
	 * 
	 * 
	 */
	public Token extraerPalabraReservadaParaBucle(String cod, int i) {
		// TODO Auto-generated method stub
		String lex;
		if (cod.charAt(i) == 'D') {
			int j = i + 1;
			if (j < cod.length() && cod.charAt(j) == 'E') {
				j++;

				if (j < cod.length() && cod.charAt(j) == 'S') {
					j++;

					lex = cod.substring(i, j);
					Token token = new Token(lex, Token.PALABRA_RESERVADA_PARA_BUCLE, j);
					return token;

				}
			}
		}
		return null;
	}

	/**
	 * Intenta extraer un identificador para el nombre de un metodo ( SI ) de la
	 * cadena cod a partir de la posicion i, basandose en el Automata
	 * 
	 * @param cod - codigo al cual se le va a intentar extraer la palabra reservada
	 *            para decision - codigo!=null
	 * 
	 * @param i   - posicion a partir de la cual se va a intentar extraer la palabra
	 *            reservada para decision
	 * @return el token palabra reservada para decision o NULL, si el token en la
	 *         posicion dada no es un separador de sentancia.El Token se compone de
	 *         el lexema, el tipo y la posicion del siguiente lexema.
	 */
	public Token extraerPalabraReservadaDecision(String cod, int i) {

		int j;
		String lex;
		if (cod.charAt(i) == 'S') {
			j = i + 1;
			if (j < cod.length() && cod.charAt(j) == 'I') {
				j++;
				lex = cod.substring(i, j);
				Token token = new Token(lex, Token.PALABRA_RESERVADA_DECISION, j);
				return token;
			}
		}

		return null;

	}

	/**
	 * Intenta extraer un identificador para el nombre de un metodo ( P b ) de la
	 * cadena cod a partir de la posicion i, basandose en el Automata
	 * 
	 * @param cod - codigo al cual se le va a intentar extraer la palabra reservada
	 *            para clase codigo!=null
	 * 
	 * @param i   - posicion a partir de la cual se va a intentar extraer la palabra
	 *            reservada - 0<=i<codigo.length()
	 * 
	 * @return el token palabra reservada para clase o NULL, si el token en la
	 *         posicion dada no es un se.El Token se compone de el lexema, el tipo y
	 *         la posicion del siguiente lexema.
	 */
	public Token extraerPalabraClase(String cod, int i) {

		int j;
		String lex;
		if (cod.charAt(i) == 'j') {
			j = i + 1;

			if (j < cod.length() && cod.charAt(j) == 'l') {
				j++;

				if (j < cod.length() && cod.charAt(j) == 'a') {
					j++;

					if (j < cod.length() && cod.charAt(j) == '$') {
						j++;

						if (j < cod.length() && cod.charAt(j) == '$') {
							j++;

							lex = cod.substring(i, j);
							Token token = new Token(lex, Token.PALABRAS_RESERVADA_CLASE, j);
							return token;
						}
					}
				}
			}
		}

		return null;
	}

	// CATEGORIA IDENTIFICADORES

	/**
	 * Este metodo intenta extraer un identidicador de variable proveniente del
	 * codigo (cod)
	 * 
	 * @param cod - codigo al cual se le va a intentar extraer la un identificador
	 *            de variable codigo!=null
	 * 
	 * @param i   - posicion a partir de la cual se va a intentar extraer el
	 *            identificador de variable - 0<=i<codigo.length()
	 * @return el token identificador de variable o NULL, si el token en la posicion
	 *         dada no es un identificador de variable.El Token se compone de el
	 *         lexema, el tipo y la posicion del siguiente lexema.
	 */

	private Token extraerIdentificadorVariable(String cod, int i) {
		// TODO Auto-generated method stub
		int j;
		String lex;
		if (LetraMin(cod.charAt(i))) {
			j = i + 1;
			if (j < cod.length() && esLetra(cod.charAt(j))) {
				j++;
				if (j < cod.length() && esLetra(cod.charAt(j))) {
					do
						j++;

					while (j < cod.length() && esLetra(cod.charAt(j)) && LetraMay(cod.charAt(j)) != true);
					lex = cod.substring(i, j);
					Token token = new Token(lex, Token.IDENTIFICADOR_VARIABLE, j);
					return token;
				}

			}
		}

		return null;
	}

	/**
	 * Intenta extraer un identificador para el nombre de un metodo ( P b ) de la
	 * cadena cod a partir de la posicion i, basandose en el Automata
	 * 
	 * @param cod - codigo al cual se le va a intentar extraer el identificador de
	 *            metodo- codigo!=null
	 * 
	 * @param i   - posicion a partir de la cual se va a intentar extraer la
	 *            identificador de metodo - 0<=i<codigo.length()
	 * @return el token Identificador de metodo o NULL, si el token en la posicion
	 *         dada no es un identificador de metodo.El Token se compone de el
	 *         lexema, el tipo y la posicion del siguiente lexema.
	 * 
	 */
	public Token extraerIdentificadorDeMetodo(String cod, int i) {
		// TODO Auto-generated method stub
		String lex;
		if (cod.charAt(i) == 'P') {
			int j = i + 1;

			if (j < cod.length() && cod.charAt(j) == 'b') {
				j++;

				lex = cod.substring(i, j);
				Token token = new Token(lex, Token.IDENTIFICADOR_DE_METODO, j);
				return token;

			}
		}
		return null;
	}

	/**
	 * Intenta extraer palabras para la clase cod a partir de la
	 * 
	 * posicion i, basandose en el Automata
	 * 
	 * @param cod - codigo al cual se le va a intentar extraer el simbolo de abrir -
	 *            codigo!=null
	 * @param i   - posicion a partir de la cual se va a intentar extraer el simbolo
	 *            de abrir 0
	 */

	public Token extraerIdentificadorClase(String cod, int i) {
		// TODO Auto-generated method stub
		// En este metodo debio ir ola$$, alternamos con la asignacion
		// ( M . N ) U L*
		String lex;
		if (i < cod.length() && LetraMay(cod.charAt(i))) {
			int j = i + 1;
			if (j < cod.length() && LetraMin(cod.charAt(j))) {
				j++;
				if (j < cod.length() && esLetra(cod.charAt(j))) {
					do
						j++;
					while (j < cod.length() && esLetra(cod.charAt(j)));
					lex = cod.substring(i, j);
					Token token = new Token(lex, Token.IDENTIFICADOR_CLASE, j);
					return token;

				}
			}
		}
		return null;
	}

	// VALORES DE ASIGNACION

	/**
	 * Intenta extraer un entero de la cadena cod a partir de la posicion i,
	 * basandose en el Automata
	 * 
	 * @param cod - codigo al cual se le va a intentar extraer un entero -
	 *            codigo!=null
	 * @param i   - posicion a partir de la cual se va a intentar extraer un entero
	 *            - 0<=indice<codigo.length()
	 * @return el token entero o NULL, si el token en la posicion dada no es un
	 *         entero. El Token se compone de el lexema, el tipo y la posicion del
	 *         siguiente lexema.
	 */

	public Token extraerEntero(String cod, int i) {

		int j;
		String lex;
		if (cod.charAt(i) == '+' || cod.charAt(i) == '-' || cod.charAt(i) == ' ') {
			j = i + 1;
			if (j < cod.length() && esDigito(cod.charAt(j))) {
				do
					j++;
				while (j < cod.length() && esDigito(cod.charAt(j)));
				lex = cod.substring(i, j);
				Token token = new Token(lex, Token.ENTERO, j);
				return token;
			}
		}

		return null;
	}

	/**
	 * Extrae un valor de asignacion REAL ((D) (D)* (.) (D)* (D))
	 * 
	 * @param cod - codigo al cual se le va a intentar extraer el valor de
	 *            asignacion de los numeros reales- codigo!=null
	 * 
	 * @param i   - posicion a partir de la cual se va a intentar extraer el valor
	 *            de asignacion de los numeros reales - 0<=i<codigo.length()
	 * @return el token valor de asignacion real o NULL, si el token en la posicion
	 *         dada no es un valor de asignacion real.El Token se compone de el
	 *         lexema, el tipo y la posicion del siguiente lexema.
	 * 
	 ***/

	private Token extraerValorAsignacionReal(String cod, int i) {
		// TODO Auto-generated method stub
		int j;
		String lex;
		if (cod.charAt(i) == '+' || cod.charAt(i) == '-' || cod.charAt(i) == ' ') {
			j = i + 1;
			if (j < cod.length() && esDigito(cod.charAt(j))) {

				j++;
				while (j < cod.length() && esDigito(cod.charAt(j))) {
					j++;
				}

				if (j < cod.length() && cod.charAt(j) == '.') {

					j++;
					while (j < cod.length() && esDigito(cod.charAt(j))) {
						j++;
					}
					j--;
					if (j < cod.length() && esDigito(cod.charAt(j))) {
						j++;
						lex = cod.substring(i, j);
						Token token = new Token(lex, Token.VALOR_ASIGNACION_REALES, j);
						return token;
					}
				}
			}
		}
		return null;
	}

	// CATEGORIA PALABRAS RESERVADAS TIPO DE DATO

	/**
	 * Intenta extraer palabra para los enteros cod a partir de la
	 * 
	 * posicion i, basondose en el Automata
	 * 
	 * @param cod - codigo al cual se le va a intentar extraer el simbolo de abrir -
	 *            codigo!=null
	 * @param i   - posicion a partir de la cual se va a intentar extraer el simbolo
	 *            de abrir 0
	 */

	public Token extraerPalabraEnteros(String cod, int i) {
		// TODO Auto-generated method stub

		// palabra enteros
		String lex;
		if (cod.charAt(i) == 'E') {
			int j = i + 1;
			if (j < cod.length() && cod.charAt(j) == 'N') {
				j++;
				if (j < cod.length() && cod.charAt(j) == 'T') {
					j++;

					lex = cod.substring(i, j);
					Token token = new Token(lex, Token.PALABRA_PARA_ENTEROS, j);
					return token;

				}
			}
		}
		return null;
	}

	/**
	 * Determina si lo ingresado corresponde a un numero real apartir de la posicion
	 * i
	 * 
	 * @param cod - codigo a cual se le va a extraer el numero real
	 * @param i   - posicion apartir del cual se va a extraer el token real
	 * @return el token real
	 */
	private Token extraerReales(String cod, int i) {
		// TODO Auto-generated method stub
		int j;
		String lex;
		if (cod.charAt(i) == 'F') {

			j = i + 1;
			lex = cod.substring(i, j);
			Token token = new Token(lex, Token.PALABRA_PARA_REAL, j);
			return token;
		}

		return null;
	}

	/**
	 * Intenta extraer una palabra reservada cadena( Chain ) de la cadena cod a
	 * partir de la posicion i, basondose en el Automata
	 * 
	 * @param cod - codigo al cual se le va a intentar extraer la palabra reservada
	 *            ( C h a i n)
	 * 
	 */
	public Token extraerPalabraReservadaParaCadenaDeCaracteres(String cod, int i) {
		// TODO Auto-generated method stub
		String lex;
		if (cod.charAt(i) == 'c') {
			int j = i + 1;
			if (j < cod.length() && cod.charAt(j) == 'h') {
				j++;
				if (j < cod.length() && cod.charAt(j) == 'a') {
					j++;
					if (j < cod.length() && cod.charAt(j) == 'i') {
						j++;
						if (j < cod.length() && cod.charAt(j) == 'n') {
							j++;

							lex = cod.substring(i, j);
							Token token = new Token(lex, Token.PALABRA_RESERVADA_PARA_CADENA_DE_CARACTERES, j);
							return token;

						}
					}
				}
			}

		}

		return null;
	}

	/**
	 * Intenta extraer palabra reservada para caracter cod a partir de la
	 * 
	 * posicion i, basondose en el Automata
	 * 
	 * @param cod - codigo al cual se le va a intentar extraer el simbolo de abrir -
	 *            codigo!=null
	 * @param i   - posicion a partir de la cual se va a intentar extraer el simbolo
	 *            de abrir 0
	 */
	public Token extraerPalabraCaracter(String cod, int i) {

		int j;
		String lex;
		if (cod.charAt(i) == 'C') {
			j = i + 1;
			if (j < cod.length() && cod.charAt(j) == 'R') {
				j++;
				lex = cod.substring(i, j);
				Token token = new Token(lex, Token.PALABRA_CARACTER, j);
				return token;
			}
		}

		return null;
	}

	// CATEGORIA NA UNA QUE SELECCIONAMOS
	/**
	 * Intenta extraer palabra reservada para una constante N/A cod a partir de la
	 * 
	 * posicion i, basondose en el Automata
	 * 
	 * @param cod - codigo al cual se le va a intentar extraer el simbolo de abrir -
	 *            codigo!=null
	 * @param i   - posicion a partir de la cual se va a intentar extraer el simbolo
	 *            de abrir 0
	 */

	public Token extraerPalabraReservadaCons(String cod, int i) {
		// TODO Auto-generated method stub

		// palabra reservada cons
		String lex;
		if (cod.charAt(i) == 'c') {
			int j = i + 1;
			if (j < cod.length() && cod.charAt(j) == 'o') {
				j++;
				if (j < cod.length() && cod.charAt(j) == 'n') {
					j++;
					if (j < cod.length() && cod.charAt(j) == 's') {
						j++;

						lex = cod.substring(i, j);
						Token token = new Token(lex, Token.PALABRA_RESERVADA_PARA_CONSTANTE, j);
						return token;

					}
				}
			}
		}
		return null;
	}

	// lO QUE POSIBLEMENTE ESTo MAL

	/**
	 * Intenta extraer valor de asignacion de caracteres cod a partir de la
	 * 
	 * posicion i, basondose en el Automata
	 * 
	 * @param cod - codigo al cual se le va a intentar extraer el simbolo de abrir -
	 *            codigo!=null
	 * @param i   - posicion a partir de la cual se va a intentar extraer el simbolo
	 *            de abrir 0
	 */

	public Token extraerAsignacionCaracter(String cod, int i) {
		// TODO Auto-generated method stub

		// Asignacion de caracter L U M U N U D U(C.S) //
		// * L U M U N U D U(C.S)

		if (cod.charAt(i) == '~') {
			int j = i + 1;

			String lex;
			if (j < cod.length() && esLetra(cod.charAt(j))) {
				// L

				// j++;
				if (j < cod.length() && cod.charAt(j) == '~') {
					j++;
					lex = cod.substring(i, j);
					Token token = new Token(lex, Token.ASIGNACION_PARA_CARACTER, j);

					return token;

				}
			}

			if (j < cod.length() && LetraMay(cod.charAt(j))) {
				// M
				j++;
				if (j < cod.length() && cod.charAt(j) == '~') {
					j++;
					lex = cod.substring(i, j);
					Token token = new Token(lex, Token.ASIGNACION_PARA_CARACTER, j);

					return token;

				}
			}

			if (j < cod.length() && LetraMin(cod.charAt(j))) {
				// N
				j++;
				if (j < cod.length() && cod.charAt(j) == '~') {
					j++;
					lex = cod.substring(i, j);
					Token token = new Token(lex, Token.ASIGNACION_PARA_CARACTER, j);

					return token;

				}
			}

			if (j < cod.length() && esDigito(cod.charAt(j))) {
				// D
				j++;
				if (j < cod.length() && cod.charAt(j) == '~') {
					j++;
					lex = cod.substring(i, j);
					Token token = new Token(lex, Token.ASIGNACION_PARA_CARACTER, j);

					return token;

				}
			}

		}

		return null;

	}

	/**
	 * Intenta extraer el valor de la cadena de caracteres que se envia por codigo
	 * (cod)
	 * 
	 * @param cod - codigo al cual se le va a intentar extraer el valor de cadena de
	 *            caracteres- codigo!=null
	 * 
	 * @param i   - posicion a partir de la cual se va a intentar extraer el valor
	 *            de cadena caracteres - 0<=i<codigo.length()
	 * 
	 * @return el token valor cadena caracteres o NULL, si el token en la posicion
	 *         dada no es un Valor cadena de caracteres.El Token se compone de el
	 *         lexema, el tipo y la posicion del siguiente lexema.
	 */
	private Token extraerValorCadenaCaracteres(String cod, int i) {

		int j;
		String lex;
		if (cod.charAt(i) == '`') {
			j = i + 1;
			if (j < cod.length() && esLetra(cod.charAt(j))) {
				j++;
				while (j < cod.length() && esLetra(cod.charAt(j))) {
					j++;
				}
				if (j < cod.length() && esDigito(cod.charAt(j))) {
					j++;
					while (j < cod.length() && esDigito(cod.charAt(j))) {
						j++;
					}
					if (j < cod.length() && cod.charAt(j) == 'o') {
						j++;
						if (j < cod.length() && cod.charAt(j) == 'o') {
							j++;
							if (j < cod.length() && cod.charAt(j) == 'o') {
								j++;
								if (j < cod.length() && cod.charAt(j) == '`') {
									j++;

									lex = cod.substring(i, j);
									Token token = new Token(lex, Token.VALOR_ASIGNACION_CADENA_CARACTERES, j);
									return token;
								}
							}
						}
					}
				}
			}
		}

		return null;

	}

	// profe
	/**
	 * Intenta extraer un operador aditivo de la cadena cod a partir de la posicion
	 * i, basondose en el Automata
	 * 
	 * @param cod - codigo al cual se le va a intentar extraer el operador aditivo -
	 *            codigo!=null
	 * @param i   - posicion a partir de la cual se va a intentar extraer el
	 *            operador aditivo - 0<=i<codigo.length()
	 * @return el token operador aditivo o NULL, si el token en la posicion dada no
	 *         es un operador aditivo.El Token se compone de el lexema, el tipo y la
	 *         posicion del siguiente lexema.
	 */
	public Token extraerOperadorAditivo(String cod, int i) {
		if (cod.charAt(i) == '+' || cod.charAt(i) == '-') {
			int j = i + 1;
			String lex = cod.substring(i, j);
			Token token = new Token(lex, Token.OPERADORADITIVO, j);
			return token;
		}
		return null;
	}

	/**
	 * Intenta extraer un identificador de la cadena cod a partir de la posicion i,
	 * basondose en el Automata
	 * 
	 * @param cod - codigo al cual se le va a intentar extraer un identficador -
	 *            codigo!=null
	 * @param i   - posicion a partir de la cual se va a intentar extraer un
	 *            identificador - 0<=indice<codigo.length()
	 * @return el token identificaror o NULL, si el token en la posicion dada no es
	 *         un identificador. El Token se compone de el lexema, el tipo y la
	 *         posicion del siguiente lexema.
	 */

	public Token extraerIdentificador(String cod, int i) {
		if (cod.charAt(i) == '_') {
			int j = i + 1;
			while (j < cod.length() && esLetra(cod.charAt(j)))
				j++;
			String lex = cod.substring(i, j);
			Token token = new Token(lex, Token.IDENTIFICADOR, j);
			return token;
		}
		return null;
	}

	/**
	 * Extraer un lexema no reconocido de la cadena cod a partir de la posicion i.
	 * Antes de utilizar este motodo, debe haberse intentado todos los otros motodos
	 * para los otros tipos de token
	 * 
	 * @param cod - codigo al cual se le va a extraer el token no reconocido -
	 *            codigo!=null
	 * @param i   - posicion a partir de la cual se va a extraer el token no
	 *            reconocido - 0<=indice<codigo.length()
	 * @return el token no reconocido. El Token se compone de lexema, el tipo y la
	 *         posicion del siguiente lexema.
	 * 
	 */
	public Token extraerNoReconocido(String cod, int i) {
		String lexema = cod.substring(i, i + 1);
		int j = i + 1;
		Token token = new Token(lexema, Token.NORECONOCIDO, j);
		return token;
	}

	/**
	 * Determina si un carocter es un digito
	 * 
	 * @param caracter - Caracter que se va a analizar - caracter!=null,
	 * @return true o false segon el caracter sea un digito o no
	 */
	public boolean esDigito(char caracter) {
		return caracter >= '0' && caracter <= '9';
	}

	/**
	 * Determina si un carocter es una letra (min o may)
	 * 
	 * @param caracter - Caracter que se va a analizar - caracter!=null,
	 * @return true o false segon el caracter sea una letra o no
	 */
	public boolean esLetra(char caracter) {
		return (caracter >= 'A' && caracter <= 'Z') || (caracter >= 'a' && caracter <= 'z');
	}

	

	
	/**
	 * Determina si un caracter es una letra mayuscula
	 */
	public boolean LetraMay(char caracter) {
		return (caracter >= 'A' && caracter <= 'Z');
	}

	/**
	 * Determina si un carocter es una letra minuscula
	 * 
	 * @param caracter - Carocter que se va a analizar - caracter!=null,
	 * @return true o false segon el carocter sea una letra minuscula o no
	 */
	public boolean LetraMin(char caracter) {
		return (caracter >= 'a' && caracter <= 'z');
	}
}


