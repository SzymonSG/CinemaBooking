package com.cinema.booking.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Cinema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cinemaId;

    @NotBlank(message = "Cinema name is required")
    @NotNull(message = "Cinema cannot be null")
    private String cinemaName;


    //lazy to jest powolne dociągnie danych z powiązanych encji
    //Cascade type.all czyli zmiany na powiąznych encjach występują
    //Użytkownik i adresy OneToMany
    // przypadek OneToMany po stronie One możemy dodać Cascade.All ponieważ usunięcie użytkownika usnie również adresy
    //Jednak ustawienie po stronie adresów moze sparwić ze usunięcie uzytkonika pozostawi serioce adresy i tym samym błędy w DB
    //mappedBy informuje o dwukierunkowości relacji oraz ze druga strona relacji jest Owenerem i tam powinno zachodzić złączenie
    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "cinemas")
    private List<Movie> movies = new ArrayList<>();






}
