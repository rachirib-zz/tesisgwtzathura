package com.clientesjson.server.exception;


/**
 *
 * @author Zathura Code Generator http://code.google.com/p/zathura
 *
 */
public class ExceptionManager {
    private static ExceptionManager instance = null;

    private ExceptionManager() {
    }

    /**
     *
     */
    public static ExceptionManager getInstance() {
        if (instance == null) {
            instance = new ExceptionManager();
        }

        return instance;
    }

    public String exceptionInGetAll() {
        return "exceptionInGetAll";
    }

    public String exceptionGetting(String tryingToGet) {
        return "exceptionGetting" + " " + tryingToGet;
    }

    public String exceptionGettingChild(String child) {
        return "exceptionGettingChild" + " " + child;
    }

    public String exceptionFindingEntityToModifyOrDelete() {
        return "exceptionFindingEntityToModifyOrDelete";
    }

    public String exceptionSaving() {
        return "exceptionSaving";
    }

    public String exceptionDeletingEntityWithChild() {
        return "exceptionDeletingEntityWithChild";
    }

    public String exceptionDeleting() {
        return "exceptionDeleting";
    }

    public String exceptionFindingEntity(String entity) {
        return "exceptionFindingEntity" + " " + entity;
    }

    public String exceptionFindingEntityForeing(String foreing) {
        return "exceptionFindingEntityForeing" + " " + foreing;
    }

    public String exceptionSelection() {
        return "exceptionSelection";
    }

    public String exceptionInEntrysWithLike(String like) {
        return "exceptionInEntrysWithLike" + " " + like;
    }
}
