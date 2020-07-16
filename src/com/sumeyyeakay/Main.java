package com.sumeyyeakay;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


import java.util.List;

/**
 * Vt ye bir sorgu gonderirsiniz ver onu session(oturum acmak) ile acarsiniz.
 * session icinde sorgu yaziyoruz ve onu yollamis oluyoruz.
 * transection yonetimi icin session yolluyoruz (ilk insert yaptin sonra uptade yaptin bunu sonradan geri almak icin session kullaniyoruz.)
 *
 * *****************************************************************************************************************************************
 *VERI TABANINDAN VERI CEKMEK ICIN KULLANILAN ISLEMLER
 *   hizlica atmak icin
 *   session.createQuery("from city") ->select * from demek
 *   countryCode='TUR' olan ya da USD olanlari getir.
 *
 *    icinde arama yapmak icin : LIKE kullan where c.name LIKE %kar% -> icinde kar olanlari getir
 *    //List<City> cities = session.createQuery("from City c where c.countryCode='TUR' AND c.district='Ankara'").getResultList();
 *
 *     Order by: Siralama yapmak istedigimiz zaman kullaniriz.  A'dan Z ye siralama default olarak tam terzi siralama -> DESC
 *
 *     Group by : datalari grup halinde almak istersek kullaniriz.
 *
 *
 *                 //2 tane columndan secmek icin
 *       List<String> countryCodes = session.createQuery(" select c.countryCode from City c GROUP BY c.countryCode").getResultList();
 *
 *       for (String countryCode:countryCodes){
 *           System.out.println(countryCode);
 *       }
 *
 * * *****************************************************************************************************************************************
 *  VERI TABANINA DEGER EKLEMEK ICIN:
 *
 *      ORM asil bu kisimda bize avantaj sagliyor.
 *
 *  ->    INSERT ISLEMI:
 *        City city = new City(4086 , "Duzce", "TUR", "Duzce", 1000000);
 *        session.save(city);
 *        System.out.println("Sehir eklendi.");
 *
 *        //vt'ye bas demek icin;
 *        session.getTransaction().commit();
 *
 * Onemli: unit of work tasarim deseninin uygulanmis seklidir.
 * Mesele siz ekleme islemini yaptiktan sonra  session.save(city); dediginiz anda eklenir.
 * Yani update yapiyor.
 *
 *
 *  -> UPDATE ISLEMI:
 *
 */
public class Main {
    public static void main(String[] args) {
        //Bir kere olusturulur
        //Configuration diye bir class var ve onu sectionFactorye atiyoruz.
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(City.class) // istedigimiz classi verebiliriz
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        //artik sorgu gonderebiliriz.

        try{
            //Transaction basladigini belirtiyorun
            session.beginTransaction();

            //insert
            System.out.println("********************* Insert Islemi *********************");
            City city = new City(4088 , "Izmir", "TUR", "Odemis", 1500000);
            session.save(city);
            System.out.println("Sehir eklendi.");

            System.out.println("********************* Update Islemi *********************");
            //update
            City city1 = session.get(City.class, 4084); //vt den getir diyoruz.
            System.out.println("VERi cekildi: " + city1.getName());
            city1.setPopulation(1300000);
            session.save(city1);
            System.out.println("Sehir guncellendi.");


            System.out.println("********************* Delete Islemi *********************");
            //delete
            City city2 = session.get(City.class, 4083);
            session.delete(city2);
            System.out.println("Sehir silindi.");

            //vt'ye bas demek icin;
            session.getTransaction().commit();

        }
        //hibernate bir hata aldiginda otomatik olarak aliyor bunun icin catch blogu yazmamiza gerek yok
        finally {
            //kapatmak icin
            factory.close();
        }


    }
}
