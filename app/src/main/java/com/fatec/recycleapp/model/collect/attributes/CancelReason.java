package com.fatec.recycleapp.model.collect.attributes;

import com.fatec.recycleapp.model.user.attributes.UserType;

public enum CancelReason {
    UNREACHABLE_LOCATION(1, "Local inacessível", UserType.TRASH_HANDLER),
    NO_MATERIALS_AVAILABLE(2, "Sem materiais disponíveis", UserType.TRASH_HANDLER),
    TIME_CONSTRAINT(3, "Restrição de tempo", UserType.TRASH_HANDLER),
    TIME_LIMIT(4, "Tempo limite excedido", UserType.TRASH_HANDLER),
    VEHICLE_ISSUE(5, "Problema no veículo", UserType.TRASH_HANDLER),
    INCORRECT_REQUEST_DETAILS(6, "Detalhes da solicitação incorretos", UserType.TRASH_HANDLER),
    SAFETY_CONCERNS(7, "Preocupação com segurança", UserType.TRASH_HANDLER),
    MATERIALS_ALREADY_COLLECTED(8, "Materiais já coletados", UserType.TRASH_PRODUCER),
    CHANGED_COLLECTION_TIME(31, "Horário de coleta alterado", UserType.TRASH_PRODUCER),
    REQUEST_MADE_BY_MISTAKE(32, "Solicitação feita por engano", UserType.TRASH_PRODUCER),
    NO_LONGER_NEEDS_COLLECTION(33, "Não preciso mais da coleta", UserType.TRASH_PRODUCER),
    INCORRECT_DETAILS_PROVIDED(34, "Detalhes incorretos fornecidos", UserType.TRASH_PRODUCER),
    OTHER(50, "Outro", null),
    NO_ANSWER(51, "Prefiro não responder", null);

    private final Integer id;
    private final String description;
    private final UserType affectedUser;

    CancelReason(Integer id, String description, UserType affectedUser) {
        this.id = id;
        this.description = description;
        this.affectedUser = affectedUser;
    }

    public Integer getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public UserType getAffectedUser() {
        return affectedUser;
    }
}
