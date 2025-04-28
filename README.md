# 🎉 Névnap GUI Projekt

![Névnap GUI](https://res.cloudinary.com/myblogki2024/image/upload/v1745842463/Amf_13_A84_V_nevnapGUI_jmkglq.png)

---

## 📚 Feladatleírás

A `nevnap.csv` fájl névnapok adatait tartalmazza az alábbi formátumban:


- Adatok elválasztója: pontosvessző (`;`)
- Fájl kódolása: `utf-8`
- Egy névhez több dátum is tartozhat!

Készítsünk egy **NevnapGUI** nevű projektet, és oldjuk meg az alábbi feladatokat!

---

## 🛠️ Feladatok

### 1. Grafikus felület kialakítása (2 pont)
- Hozzuk létre a mintán látható GUI-t.
- A **Fájl** menü tartalmazza:
  - `Megnyitás` (`Ctrl + O`)
  - `Kilépés` (`Ctrl + Q`)
- A **ComboBox** elemben legyenek választhatók a hónapok (**alapértelmezett: Január**).
- A lista mérete: **500×300 pixel**, és kövesse az ablak méretét.
- A GUI elemek NE érjenek össze!

---

### 2. Fájlkezelés és adatbetöltés
- `Megnyitás` menüpontnál válasszunk ki egy `*.csv` fájlt a projekt mappájából. (1 pont)
- Töltsük be az adatokat egy megfelelő adatszerkezetbe. (1 pont)
- A kiválasztott hónap szerint jelenítsük meg az adatokat a listában, **növekvő nap sorrendben**! (1 pont)
- A megjelenítés hasonlítson a mintára. (1 pont)

---

### 3. Hónapváltás dinamikusan
- Másik hónap kiválasztásakor frissüljön a lista tartalma. (1 pont)
- A ComboBox CSAK akkor legyen aktív, ha már sikeres fájlmegnyitás történt! (1 pont)

---

### 4. Extra funkciók
- A **Súgó** menüben legyen egy `Névjegy` menüpont, amely felugró ablakban információt ad. (1 pont)
- A **Fájl** menü `Kilépés` pontja zárja be a programot! (1 pont)

---

## 🆘 Segítségkód

```java
private FileChooser fc = new FileChooser();
fc.setInitialDirectory(new File("./"));
fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Szövegfájlok", "*.txt"));
File fbe = fc.showOpenDialog(lsLista.getScene().getWindow());


---

# 💡 Plusz megjegyzés
- Figyelj a `utf-8` kódolásra fájl megnyitásakor!
- Az adatszerkezet használatánál törekedj arra, hogy hatékonyan kezeld a név-nap párokat.

---
