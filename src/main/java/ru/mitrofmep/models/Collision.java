package ru.mitrofmep.models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.Date;

public class Collision {

    private int id;

    // Статус пересечения
    @NotEmpty(message = "Поле не заполнено")
    @Size(min = 2, max = 30, message = "Статус должен быть короче 30 символов")
    private String status;

    // Раздел проектирования
    @NotEmpty(message = "Поле не заполнено")
    @Size(min = 2, max = 10, message = "Наименование раздела должно быть короче 10 символов")
    private String service;

    // ID 1-го элемента
    @Positive(message = "Некорректное значение")
    private int idElement1;

    // ID 2-го элемента
    @Positive(message = "Некорректное значение")
    private int idElement2;

    public Collision(String status, String service, int idElement1, int idElement2) {
        this.status = status;
        this.service = service;
        this.idElement1 = idElement1;
        this.idElement2 = idElement2;
    }

    public Collision() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public int getIdElement1() {
        return idElement1;
    }

    public void setIdElement1(int idElement1) {
        this.idElement1 = idElement1;
    }

    public int getIdElement2() {
        return idElement2;
    }

    public void setIdElement2(int idElement2) {
        this.idElement2 = idElement2;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}