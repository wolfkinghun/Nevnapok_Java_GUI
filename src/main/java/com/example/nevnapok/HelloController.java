package com.example.nevnapok;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;

public class HelloController {
    @FXML private ComboBox<String> cmHonap; // ComboBox a hónapok kiválasztásához
    @FXML private ListView<String> lsLista; // ListView a névnapok listázásához

    // Belső osztály egy név és a hozzá tartozó napok tárolására
    private class Nev {
        public String nev;
        public ArrayList<String> napok;

        public Nev(String sor) {
            // Sor feldarabolása pontosvessző mentén (név;dátum;dátum;...)
            String[] s = sor.split(";");
            nev = s[0];
            napok = new ArrayList<>();
            for (int i=1; i<s.length; i++) napok.add(s[i]);
        }
    }

    private ArrayList<Nev> nevek = new ArrayList<>(); // Nevek listája
    private FileChooser fc = new FileChooser(); // Fájlválasztó párbeszédablak

    // Hónapok nevei egy tömbben
    String[] honev = { "", "Január", "Február", "Március", "Április", "Május", "Június", "Július", "Augusztus", "Szeptember", "Október", "November", "December" };

    public void initialize() {
        // ComboBox feltöltése hónapnevekkel
        for (int i=1; i<=12; i++) cmHonap.getItems().add(honev[i]);
        cmHonap.getSelectionModel().select(0); // Alapértelmezett kiválasztás
        fc.setInitialDirectory(new File("./")); // Kezdőkönyvtár beállítása
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV fájlok", "*.csv")); // Csak CSV fájlok engedélyezése
    }

    @FXML private void szures() {
        // Szűrés a kiválasztott hónap alapján
        int h = cmHonap.getSelectionModel().getSelectedIndex();
        if (h != -1) {
            lsLista.getItems().clear(); // Lista kiürítése
            String hs = String.format("%02d", h+1); // Hónap számmá alakítása (pl. 03)
            TreeMap<String, String> napMap = new TreeMap<>(); // Napokhoz rendelt nevek
            for (Nev nev : nevek) {
                for (String nap : nev.napok) {
                    if (nap.substring(0,2).equals(hs)) {
                        // Ha az adott nap az aktuális hónapban van
                        if (!napMap.containsKey(nap)) napMap.put(nap, nev.nev);
                        else napMap.put(nap, napMap.get(nap) + ", " + nev.nev);
                    }
                }
            }
            // A listába kiíratjuk a napokat és a hozzájuk tartozó neveket
            for (String nap : napMap.keySet()) lsLista.getItems().add(nap + ":  " + napMap.get(nap));
        }
    }

    @FXML private void onMegnyitasClick() {
        // Fájl megnyitása párbeszédablakkal
        File fbe = fc.showOpenDialog(cmHonap.getScene().getWindow());
        if (fbe != null) {
            betolt(fbe); // Adatok betöltése a fájlból
            cmHonap.setDisable(false); // ComboBox engedélyezése
            szures(); // Azonnali szűrés
        }
    }

    private void betolt(File fajl) {
        // Fájl beolvasása soronként
        Scanner be = null;
        try {
            be = new Scanner(fajl, "utf-8");
            while (be.hasNextLine()) nevek.add(new Nev(be.nextLine())); // Új Nev objektum létrehozása minden sorból
        } catch (Exception e) {
            e.printStackTrace(); // Hiba esetén kiíratás
        } finally {
            if (be != null) be.close(); // Scanner bezárása
        }
    }

    @FXML private void onNevjegyClick() {
        // Névjegy megjelenítése információs ablakban
        Alert info = new Alert(Alert.AlertType.INFORMATION);
        info.setTitle("Névjegy");
        info.setHeaderText(null);
        info.setContentText("Névnap v1.0\n(C) Kandó");
        info.showAndWait();
    }

    @FXML private void onKilepesClick() {
        // Alkalmazás bezárása
        Platform.exit();
    }
}
