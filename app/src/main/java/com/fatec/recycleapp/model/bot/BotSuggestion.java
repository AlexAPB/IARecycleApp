package com.fatec.recycleapp.model.bot;

public enum BotSuggestion {
    RECYCLE(1, "Como reciclar", "Como reciclar este material?"),
    CHANGE(2, "Outro material", "Quero falar sobre outro tipo de material."),
    DISPOSAL(3, "Descarte correto", "Onde descartar este item de forma correta?"),
    REUSE(4, "Reutilizar", "Como posso reutilizar este material antes de reciclá-lo?"),
    COMPOST(5, "Compostagem", "Este item pode ser compostado?"),
    INFO(6, "Mais informações", "Gostaria de saber mais sobre este material."),
    LOCATE(7, "Ponto de coleta", "Onde encontrar um ponto de coleta próximo?"),
    REDUCE(8, "Reduzir resíduos", "Como posso reduzir o uso deste material?"),
    TIP(9, "Dica ecológica", "Qual dica sustentável você tem para este material?"),
    MATERIAL_TYPE(10, "Tipo de material", "Este material é reciclável ou não?"),
    LAW(11, "Legislação", "Existem leis sobre o descarte deste material?"),
    IMPACT(12, "Impacto ambiental", "Qual é o impacto ambiental deste material?"),
    GUIDELINE(13, "Orientações gerais", "Quais são as orientações gerais sobre reciclagem em geral?"),
    ERROR(14, "Erro no descarte", "Cometi um erro ao reciclar. Como corrigir?");

    private final Integer id;
    private final String abbreviated;
    private final String phrase;

    BotSuggestion(Integer id, String abbreviated, String phrase) {
        this.id = id;
        this.abbreviated = abbreviated;
        this.phrase = phrase;
    }

    public Integer getId() {
        return id;
    }

    public String getAbbreviated() {
        return abbreviated;
    }

    public String getPhrase() {
        return phrase;
    }
}
