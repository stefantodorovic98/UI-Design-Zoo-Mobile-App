package com.example.pki;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.pki.models.Animal;
import com.example.pki.models.Event;
import com.example.pki.models.Notification;
import com.example.pki.models.Packet;
import com.example.pki.models.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Util {

    public static void initData(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();

        // Logged user
        User loggedUser = new User("1", "1", "Petar",
                "Petrovic", "062643213", "Sarajevska 45");
        String json = gson.toJson(loggedUser);
        editor.putString("loggedUser", json);

        // Notifications
        List<Notification> notificationList = new ArrayList<>();
        notificationList.add(new Notification("Zaposleni je ODOBRIO ulaznicu", "29.04.2022"));
        notificationList.add(new Notification("Zaposleni je ODBIO grupnu ulaznicu", "31.08.2022"));

        String notificationJson = gson.toJson(notificationList);
        editor.putString("notifications", notificationJson);

        // Animals
        List<Animal> animalList = new ArrayList<>();
        animalList.add(new Animal("Tigar", R.drawable.tigar, "Tigar (lat. Panthera tigris)" +
                        " je sisar iz porodice mačaka (Felidae) i jedan od četiri vrste „velikih mačaka“" +
                        " roda pantera (Panthera). On je vrhunski predator i najveća živa mačka na svetu." +
                        "U brojnim istorijskim mitovima istočnjačkih zemalja tigar je kralj svih zveri. " +
                        "Kao ugrožene vrste, većina svetskih tigrova danas živi u zatočeništvu ljudi."));
        animalList.add(new Animal("Beloglavi sup", R.drawable.lesinar,"Beloglavi sup (lat. Gyps fulvus)" +
                " je velika ptica lešinar iz familije Accipitridae. Kao i drugi lešinari hrani se strvinama, pretežno" +
                " uginulim životinjama koje nalazi krstareći nebom širokim područjima, često leteći u jatima. Često" +
                " grokće i šišti naročito kada se hrani. Gnezdi se na liticama planina ležući po jedno jaje. Ponekad" +
                " formiraju kolonije.\n"));
        animalList.add(new Animal("Pirana", R.drawable.pirana,"Pirana (latinski: pygocentrus piraya) je" +
                " slatkovodna riba iz porodice Characidae iz reda Characiformes rasprostranjena po rekama i jezerima Južne" +
                " Amerike. Poznata je po svojim oštrim zubima. Iako se često opisuju kao ekstremno predatorske i uglavnom" +
                " se hrane ribom. Njihove prehrambene navike se znatno razlikuju, a takođe konzumiraju biljni materijal. " +
                "Klasifikuju se kao omnivori."));
        animalList.add(new Animal("Vuk", R.drawable.vuk,"Vuk (lat. Canis lupus), poznat i kao sivi vuk," +
                " vrsta je iz roda Canis koja živi u divljini i udaljenim područjima Evroazije i Sjeverne Amerike. Najveći" +
                " je postojeći član svoje porodice. Razlikuje se od ostalih vrsta roda Canis po manje istaknutim osobinama," +
                " naročito na ušima i njušci. Zimsko krzno je dugo i gusto i pretežno šareno sive boje."));
        animalList.add(new Animal("Medved", R.drawable.medved,"Medvedi (ijek. medvjedi; lat. Ursidae) su" +
                " porodica krupnih sisara iz reda zveri. Medvedi žive u različitim staništima, od tropskih do polarnih i od" +
                " planinskih do ravničarskih. Od staništa zavisi način ishrane medveda, mada je većina vrsta medveda omnivorna." +
                " Najveći broj vrsta medveda se hrani korenjem, bobicama, ribom."));
        animalList.add(new Animal("Lisica", R.drawable.lisica,"Lisice su sisari manje ili osrednje veličine i" +
                " pripadaju nekolikim rodovima porodice pasa (lat. sanidae). Imaju pljosnatu lobanju, uspravne trouglaste uši," +
                " zašiljenu, blago okrenutu njušku i dugi grmoliki rep.Lisice su lovljene uz pomoć čopora pasa gonič. Izvožene su" +
                " u razne delove novog sveta od strane evropskih doseljenika."));
        animalList.add(new Animal("Jazavac", R.drawable.jazavac,"Jazavac ili evropski jazavac (lat. Meles meles)" +
                " je vrsta životinje iz roda jazavac (lat. Meles), koji pripada porodici kuna. Uglavnom živi u odajama ispod" +
                " zemlje koje sam kopa, a koje su hodnicima povezane sa površinom. Jazavac je pretežno noćna životinja, tako" +
                " da ima slabo razvijen vid, nešto bolje razvijen sluh i odlično razvijeno čulo mirisa."));
        animalList.add(new Animal("Pčela", R.drawable.pcela,"Pčele su leteći insekti, bliski rođaci bumbara," +
                " a dalji ose i mrava. Na svetu ima približno 20 000 vrsta pčela, te se nalaze na svim kontinentima osim" +
                " Antarktika. Hrane se nektarom primarno kao izvorom energije, te polenom, kao izvorom proteina."));
        animalList.add(new Animal("Žaba", R.drawable.zaba,"Žabe pripadaju vodozemcima, a razlikuju se od drugih" +
                " vodozemaca po tome što nemaju rep. Tijelo im je takođe drugačije. Zadnje noge su im znatno duže od prednjih," +
                " na kojim se nalazi 4 ili 5 prstiju. Zadnje noge su prilagođene za tzv. katapultarni skok."));
        animalList.add(new Animal("Žirafa", R.drawable.zirafa,"Žirafa (Giraffa camelopardalis) je afrički sisar" +
                " iz reda papkara, najviši od svih kopnenih životinja. Žirafe su srodstvu sa jelenima, antilopama i govedima," +
                " ali su grupisane u zasebnu familiju, familiju žirafa (Giraffidae) u koju još spada njihov najbliži rođak, okapi." +
                " Žirafe žive na prostoru od Čada do Južne Afrike."));

        String animalJson = gson.toJson((animalList));
        editor.putString("animals", animalJson);

        // Events
        List<Event> eventList = new ArrayList<>();
        eventList.add(
                new Event("Tigrovi dani", "Od 24.04. do 29.04." +
                        " dođite i povedite svoje najmilije! Za najmlađe će biti" +
                        " organizovane radionice!", 48, R.drawable.tigar));
        eventList.add(
                new Event("Sa vodozemcima na ti", "Da li želite da saznate sve" +
                        " o vodozemcima? Dođite 31.08. i naučite nešto novo! Kupci ulaznice za Gold" +
                        " paket dobijaju igračku žabu na poklon!", 17, R.drawable.vodozemci));

        eventList.add(
                new Event("Sve o beloglavim supovima", "Dana 21.09. će se održati" +
                        " posebna radionica o beloglavim supovima! Tog dana će ulaznice za Bronze i" +
                        " Silver pakete biti snižene 30%!", 52, R.drawable.lesinar));

        eventList.add(
                new Event("Pčela - čuvar sveta", "Zašto su pčele bitne? Kako nastaje med?" +
                        " Odogovor na ova pitanja i na mnoga druga ćete dobiti na specijalnoj radionici koja će" +
                        " se održati 29.09. u našem zoološkom vrtu! Vidimo se :)", 104, R.drawable.pcela));

        eventList.add(
                new Event("Pirane", "Sve o ovoj interesantnoj vrsti ribe ćete moći da" +
                        " saznate 3.10. u 12h. Tada će u našem zoološkom vrtu da održi radionicu jedan od" +
                        " najvećih ihtiologa današnjice, dr John Harris!", 12, R.drawable.pirana));

        String eventJson = gson.toJson((eventList));
        editor.putString("events", eventJson);

        // Packets
        List<Packet> packetList = new ArrayList<>();
        packetList.add(new Packet("Bronze paket", "Boravak 2h u zoološkom vrtu uz" +
                " pratnju vodiča! Sok gratis!", R.drawable.bronze_paket));
        packetList.add(new Packet("Silver paket", "Boravak 4h u zoološkom vrtu uz" +
                " pratnju vodiča! Sok i sendvič gratis!", R.drawable.silver_paket));
        packetList.add(new Packet("Gold paket", "Boravak 4h u zoološkom vrtu uz" +
                " pratnju vodiča! Slikanje sa papagajem! Sok, sendvič i sladoled gratis!", R.drawable.gold_paket));

        String packetJson = gson.toJson((packetList));
        editor.putString("packets", packetJson);
        editor.apply();
    }

    public static void setLoggedUser(Context context, User loggedUser) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(loggedUser);
        editor.putString("loggedUser", json);
        editor.apply();
    }

    public static User getLoggedUser(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("loggedUser", "");
        User loggedUser = gson.fromJson(json, User.class);
        return loggedUser;
    }

    public static void setNotifications(Context context, List<Notification> notificationList) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();

        String notificationJson = gson.toJson(notificationList);
        editor.putString("notifications", notificationJson);
        editor.apply();
    }

    public static List<Notification> getNotifications(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("notifications", "");
        Type type = TypeToken.getParameterized(ArrayList.class, Notification.class).getType();
        ArrayList<Notification> notificationList = gson.fromJson(json, type);
        return notificationList;
    }

    public static void setAnimals(Context context, List<Animal> animalList) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();

        String animalJson = gson.toJson(animalList);
        editor.putString("animals", animalJson);
        editor.apply();
    }

    public static List<Animal> getAnimals(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("animals", "");
        Type type = TypeToken.getParameterized(ArrayList.class, Animal.class).getType();
        ArrayList<Animal> animalList = gson.fromJson(json, type);
        return animalList;
    }

    public static Animal getAnimalForIndex(Context context, int index) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("animals", "");
        Type type = TypeToken.getParameterized(ArrayList.class, Animal.class).getType();
        ArrayList<Animal> animalList = gson.fromJson(json, type);
        Animal animal = animalList.get(index);
        return animal;
    }

    public static void setEvents(Context context, List<Event> eventList) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();

        String eventJson = gson.toJson(eventList);
        editor.putString("events", eventJson);
        editor.apply();
    }

    public static List<Event> getEvents(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("events", "");
        Type type = TypeToken.getParameterized(ArrayList.class, Event.class).getType();
        ArrayList<Event> eventList = gson.fromJson(json, type);
        return eventList;
    }

    public static List<Packet> getPackets(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("packets", "");
        Type type = TypeToken.getParameterized(ArrayList.class, Packet.class).getType();
        ArrayList<Packet> packetList = gson.fromJson(json, type);
        return packetList;
    }

}
