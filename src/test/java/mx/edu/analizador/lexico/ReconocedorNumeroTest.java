package mx.edu.analizador.lexico;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ReconocedorNumeroTest {
    private final ReconocedorNumero reconocedorNumero = new ReconocedorNumero();

    @Test
    void testEnteroSimple() {
        Cursor c = new Cursor("42");
        assertTrue(reconocedorNumero.puedeIniciar(c));
        Token token = reconocedorNumero.leer(c);

        assertEquals("42", token.lexema());
        assertEquals(TipoToken.CONSTANTE_ENTERA, token.tipo());
        assertEquals(1, token.inicio());
        assertEquals(2, token.fin());
    }

    @Test
    void testRealSimple() {
        Cursor c = new Cursor("3.14");
        Token token = reconocedorNumero.leer(c);

        assertEquals("3.14", token.lexema());
        assertEquals(TipoToken.CONSTANTE_REAL, token.tipo());
    }

    @Test
    void testRealDobleDecimal() {
        Cursor c = new Cursor("1.5.3");
        Token token = reconocedorNumero.leer(c);

        assertEquals("1.5", token.lexema());
        assertEquals(TipoToken.CONSTANTE_REAL, token.tipo());
        assertEquals('.', c.actual());
    }

    @Test
    void testPuntoAlFinal() {
        Cursor c = new Cursor("7.");
        Token token = reconocedorNumero.leer(c);

        assertEquals("7", token.lexema());
        assertEquals(TipoToken.CONSTANTE_ENTERA, token.tipo());
        assertEquals('.', c.actual());
    }
}
