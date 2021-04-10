package br.com.posjava.resource.dto;

public class BoletimDTO {

    private Long positivos;
    private Long negativos;
    private String agrupador;

    public BoletimDTO() {
    }

    public BoletimDTO(Long positivos, Long negativos) {
        this.positivos = positivos;
        this.negativos = negativos;
    }

    public Long getPositivos() {
        return positivos;
    }

    public void setPositivos(Long positivos) {
        this.positivos = positivos;
    }

    public Long getNegativos() {
        return negativos;
    }

    public void setNegativos(Long negativos) {
        this.negativos = negativos;
    }

    public String getAgrupador() {
        return agrupador;
    }

    public void setAgrupador(String agrupador) {
        this.agrupador = agrupador;
    }
}
