package modelo;

/**
 *
 * @author abelc
 */
public class CasillaModelo {

    private String tipo;
    private FichaModelo ocupadoPor;
    private int numCasilla;

    public CasillaModelo() {
    }

    public CasillaModelo(String tipo, int numCasilla) {
        this.tipo = tipo;
        this.ocupadoPor = null;
        this.numCasilla = numCasilla;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public FichaModelo getOcupadoPor() {
        return ocupadoPor;
    }

    public void setOcupadoPor(FichaModelo ocupadoPor) {
        this.ocupadoPor = ocupadoPor;
    }

    public int getNumCasilla() {
        return numCasilla;
    }

    public void setNumCasilla(int numCasilla) {
        this.numCasilla = numCasilla;
    }

}
