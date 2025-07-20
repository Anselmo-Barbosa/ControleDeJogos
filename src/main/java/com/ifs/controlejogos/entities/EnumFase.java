package com.ifs.controlejogos.entities;

public enum EnumFase {
    CLASSIFICATORIA,
    OITAVAS,
    QUARTAS,
    SEMIFINAL,
    FINAL;

    //Metodo auxiliar pra pegar a proxima fase com base na vase atual
    public EnumFase proxima() {
        switch (this) {
            case OITAVAS:
                return QUARTAS;
            case QUARTAS:
                return SEMIFINAL;
            case SEMIFINAL:
                return FINAL;
            default:
                return null;
        }
    }
}

