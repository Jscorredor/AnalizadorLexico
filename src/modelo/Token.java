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

/**
 * Clase que modela un token
 */

public class Token {
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------
    /**
     * Constantes para modelar los posibles tipos de token que se van a analizar
     */
	
	
	//CATEGORIA OPERADORES
	final public static String OPERADOR_ARITMETICO= "OPERADOR ARITMETICO"; //SUM, REST, MULT, DIV, SUME, MOD
	final public static String OPERADOR_RELACIONAL = "Operador relacional"; //SW, MW, SE, ME, EE, NEGE
	final public static String OPERADOR_LOGICO ="Operador Logico"; //AND OR NEG
    final public static String OPERADORASIGNACION = "Operador de asignacion"; //E
    final public static String SIMBOLO_DE_ABRIR= "Simbolo para Abrir"; //┐, <<, »
    final public static String SIMBOLO_CERRAR ="Simbolo para Cerrar"; //?, w, z
    final public static String OPERADOR_TERMINAL_INICIAL = "Operador Terminal y/o Inicial"; //.
    final public static String SEPARADOR_SENTENCIA = "Separador de Sentencia"; // a
    
    //CATEGORIA PALABRAS RESERVADAS DIFERENTES USOS
    final public static String PALABRA_RESERVADA_PARA_BUCLE="PALABRA RESERVADA PARA BUCLE O CICLO"; //DES
    final public static String PALABRA_RESERVADA_DECISION = "Palabra reservada decision"; //SI
    final public static String PALABRAS_RESERVADA_CLASE = "Palabra reservada clase"; //la$$
    
    //CATEGORIA IDENTIFICADORES
    final public static String IDENTIFICADOR_VARIABLE = "Identificador de Variable"; //ajJJ^* NLL^*
    final public static String IDENTIFICADOR_DE_METODO ="IDENTIFICADOR DE METODO"; //Pb
    final public static String IDENTIFICADOR_CLASE = "Identificador de Clase"; //(M.N)Ul^*
    
    //CATEGORIA VALORES DE ASIGNACION
    final public static String ENTERO = "Entero"; //+07
    final public static String VALOR_ASIGNACION_REALES = "Reales"; //+7.7
    
    //CATEGORIA PALABRAS RESERVADAS TIPO DE DATO
    final public static String PALABRA_PARA_ENTEROS = "Palabra Para Enteros"; //ENT
    final public static String PALABRA_PARA_REAL ="Palabra para real"; //F
    final public static String PALABRA_RESERVADA_PARA_CADENA_DE_CARACTERES="PALABRA RESERVADA PARA CADENAS DE CARACTERES"; //chain
    final public static String PALABRA_CARACTER = "Palabra caracter"; //CR
    
    
    //CATEGORIA UNA A UNA QUE SELECCIONAMOS
    final public static String PALABRA_RESERVADA_PARA_CONSTANTE = "Palabra Reservada Para Constante N/A Elegida"; //cons
    
    

   
    
    //Metodos profe
    final public static String OPERADORADITIVO = "Operador aditivo";
    final public static String IDENTIFICADOR = "Identificador";
    final public static String NORECONOCIDO = "No reconocido";
    
    
    
    //Posibles errores
    
    final public static String ASIGNACION_PARA_CARACTER = "Valor de Asignacion Para Caracter"; // L U M U N U D U(C.S)
    
    final public static String VALOR_ASIGNACION_CADENA_CARACTERES = "Valor de asignacion cadena de caracteres";
    
    
    
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------
    /**
     * Lexema
     */
    private String lexema;

    /**
     * tipo
     */
    private String tipo;

    /**
     * posicion del siguiente lexema
     */
    private int indiceSiguiente;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------
    /**
     * Constructor de un token
     * @param elLexema - cadena - laCadena != null
     * @param elTipo - tipo del token - elTipo != null
     * @param elIndiceSiguiente - posicion del siguiente token - laPosicionSiguiente > 0
     */
    public Token( String elLexema, String elTipo, int elIndiceSiguiente )
    {
        lexema = elLexema;
        tipo = elTipo;
        indiceSiguiente = elIndiceSiguiente;
    }

    // -----------------------------------------------------------------
    // Metodos
    // -----------------------------------------------------------------

    /**
     * Entrega la informacion del token
     * @return Descripcion del token
     */
    public String darDescripcion( )
    {
        return "Token: " + lexema + "     Tipo: " + tipo + "     indice del siguiente: " + indiceSiguiente;
    }

    /**
     * Metodo que retorna el lexema del token
     * @return el lexema del token
     */
    public String darLexema( )
    {
        return lexema;
    }

    /**
     * Metodo que retorna la posicion del siguiente lexema
     * @return posicion del siguiente token
     */
    public int darIndiceSiguiente( )
    {
        return indiceSiguiente;
    }

    /**
     * Metodo que retorna el tipo del token
     * @return el tipo del token
     */
    public String darTipo( )
    {
        return tipo;
    }




}
