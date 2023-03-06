package uz.ataboyev.warehouse.component;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import uz.ataboyev.warehouse.entity.*;
import uz.ataboyev.warehouse.enums.Type;
import uz.ataboyev.warehouse.repository.*;

import java.util.ArrayList;
import java.util.List;
//import uz.ataboyev.warehouse.service.autoBackup.BackupService;


@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
    private final CompanyRepository companyRepository;
    private final WarehouseRepository warehouseRepository;
    private final ClientRepository clientRepository;
    private final ProductCompanyRepository brandRepository;
    private final ProductRepository productRepository;
    public static final String BOSS_NAME = "Begzod boss";
    public static final Company naz = new Company("NAZ");
    public static final Warehouse wh1 = new Warehouse("Sklad 1", naz.getId());

//    private final BackupService backupService;


    @Value("${dataLoaderMode}")
    private String dataLoaderMode;


    @Override
    public void run(String... args) throws Exception {
        if (dataLoaderMode.equals("always"))
        saveDefaultLoader();
    }
    private void initMethods() {
    }


    private void saveDefaultLoader() {
        companyRepository.save(naz);
        warehouseRepository.save(wh1);
        warehouseRepository.save(new Warehouse("Sklad 2", naz.getId()));
        List<Client> clients = new ArrayList();

        //CLIENTLAR
        clients.add(new Client(wh1.getId(), Type.COSTUMER, "7 Dokon  Nozim Rasxod", "+998 ** *** ** **"));
        clients.add(new Client(wh1.getId(),Type.COSTUMER, "7 Dokon  Nozim Savdo", "+998 ** *** ** **"));
        clients.add(new Client(wh1.getId(),Type.COSTUMER, "4 Dokon Aziz Rasxod", "+998 ** *** ** **"));
        clients.add(new Client(wh1.getId(),Type.COSTUMER, "4 Dokon Aziz Savdo", "+998 ** *** ** **"));
        clients.add(new Client(wh1.getId(),Type.COSTUMER, "53 Dokon Sardor Rasxod", "+998 ** *** ** **"));
        clients.add(new Client(wh1.getId(),Type.COSTUMER, "53 Dokon Sardor Savdo", "+998 ** *** ** **"));
        clients.add(new Client(wh1.getId(),Type.COSTUMER, "Orikzor Ismat", "+998 ** *** ** **"));
        clients.add(new Client(wh1.getId(),Type.COSTUMER, "Qoyliq Akmal", "+998 ** *** ** **"));
        clients.add(new Client(wh1.getId(),Type.COSTUMER, "Gvardeyskiy Sharofiddin", "+998 ** *** ** **"));
        clients.add(new Client(wh1.getId(),Type.COSTUMER, "Solnechniy Baxtiyor", "+998 ** *** ** **"));
        clients.add(new Client(wh1.getId(),Type.COSTUMER, "Sanjar Jomiy", "+998 ** *** ** **"));
        clients.add(new Client(wh1.getId(),Type.COSTUMER, "Begzodni kliyenti Jalil", "+998 ** *** ** **"));
        clients.add(new Client(wh1.getId(),Type.COSTUMER, "Islom Jomiy 94 6293212", "+998 ** *** ** **"));
        clients.add(new Client(wh1.getId(),Type.COSTUMER, "Feruz Navoiy", "+998 ** *** ** **"));
        clients.add(new Client(wh1.getId(),Type.COSTUMER, "Dilshod Chirchiq", "+998 ** *** ** **"));
        clients.add(new Client(wh1.getId(),Type.COSTUMER, "SARDOR GVARDIYA", "+998 ** *** ** **"));
        clients.add(new Client(wh1.getId(),Type.COSTUMER, "Nozim 7 dokon hos hisob", "+998 ** *** ** **"));
        clients.add(new Client(wh1.getId(),Type.COSTUMER, "Xudoyberdi gvardiya", "+998 ** *** ** **"));
        clients.add(new Client(wh1.getId(),Type.COSTUMER, "Faxriddin chuqursoy", "+998 ** *** ** **"));
        clients.add(new Client(wh1.getId(),Type.COSTUMER, "Lakakraska Abduvoxid", "+998 ** *** ** **"));
        clients.add(new Client(wh1.getId(),Type.COSTUMER, "Olim aka jomiy 97-3304767", "+998 ** *** ** **"));
        // postavshiklarwh1.getId(),
        clients.add(new Client(wh1.getId(),Type.CONSUMER, "Boou smesit pul", "+998 ** *** ** **"));
        clients.add(new Client(wh1.getId(),Type.CONSUMER, " Miraj tovari Prixod", "+998 ** *** ** **"));
        clients.add(new Client(wh1.getId(),Type.CONSUMER, "Olim AC ceramik prihod", "+998 ** *** ** **"));
        clients.add(new Client(wh1.getId(),Type.CONSUMER, "Nova plastik prihod", "+998 ** *** ** **"));
        clients.add(new Client(wh1.getId(),Type.CONSUMER, "Olim sushilka prixod", "+998 ** *** ** **"));
        clients.add(new Client(wh1.getId(),Type.CONSUMER, "Abdullo mebel prixod", "+998 ** *** ** **"));
        clients.add(new Client(wh1.getId(),Type.CONSUMER, "Cac keramik prixod", "+998 ** *** ** **"));
        clients.add(new Client(wh1.getId(),Type.CONSUMER, "Dilmurod prixod", "+998 ** *** ** **"));
        clients.add(new Client(wh1.getId(),Type.CONSUMER, "Nizomiddin prixod", "+998 ** *** ** **"));
        clients.add(new Client(wh1.getId(),Type.CONSUMER, "Smoow poddon prixod", "+998 ** *** ** **"));
        clients.add(new Client(wh1.getId(),Type.CONSUMER, "EKOKERAMA prixod", "+998 ** *** ** **"));
        clients.add(new Client(wh1.getId(),Type.CONSUMER, "ROSA  prixod", "+998 ** *** ** **"));
        clients.add(new Client(wh1.getId(),Type.CONSUMER, "Xitoy mol prixod SMOOW", "+998 ** *** ** **"));
        clients.add(new Client(wh1.getId(),Type.CONSUMER, "Boou smesit olib ketgan mol", "+998 ** *** ** **"));
        clients.add(new Client(wh1.getId(),Type.CONSUMER, "Grand Keramik prixod", "+998 ** *** ** **"));
        clients.add(new Client(wh1.getId(),Type.CONSUMER, "GlassExpo prixod oyna", "+998 ** *** ** **"));
        clients.add(new Client(wh1.getId(),Type.CONSUMER, "Delux rakvina", "+998 ** *** ** **"));
        clients.add(new Client(wh1.getId(),Type.CONSUMER, "Namangan temir poddon", "+998 ** *** ** **"));
        clients.add(new Client(wh1.getId(),Type.CONSUMER, "Calori smesitel", "+998 ** *** ** **"));
        clients.add(new Client(wh1.getId(),Type.CONSUMER, "Xitoy anton chunxay", "+998 ** *** ** **"));
        clients.add(new Client(wh1.getId(),Type.CONSUMER, "Farhod akril vanna", "+998 ** *** ** **"));
        clients.add(new Client(wh1.getId(),Type.CONSUMER, "Coco prihod", "+998 ** *** ** **"));
        clients.add(new Client(wh1.getId(),Type.CONSUMER, "Idel Vanna poddon", "+998 ** *** ** **"));
        clients.add(new Client(wh1.getId(),Type.CONSUMER, "BOOU PRIHOD", "+998 ** *** ** **"));
        clients.add(new Client(wh1.getId(),Type.CONSUMER, "Naz sklad Savdo", "+998 ** *** ** **"));
        clients.add(new Client(wh1.getId(),Type.CONSUMER, "Santek prixod", "+998 ** *** ** **"));
        clients.add(new Client(wh1.getId(),Type.CONSUMER, "GESSO santexnika", "+998 ** *** ** **"));
        clients.add(new Client(wh1.getId(),Type.CONSUMER, "White ceramika santexnika", "+998 ** *** ** **"));
        clients.add(new Client(wh1.getId(),Type.CONSUMER, "TURGAY", "+998 ** *** ** **"));
        clients.add(new Client(wh1.getId(),Type.CONSUMER, "Рустам Дониёр", "+998 ** *** ** **"));
        clients.add(new Client(wh1.getId(),Type.CONSUMER, "R-50 terminal", "+998 ** *** ** **"));
        clients.add(new Client(wh1.getId(),Type.WORKER, "Dilshod Nasirov", "+998 ** *** ** **"));
        clients.add(new Client(wh1.getId(),Type.WORKER, "Begzod", "+998 ** *** ** **"));
        clientRepository.saveAll(clients);

        List<ProductCompany> brands = new ArrayList();
        ProductCompany smoow = new ProductCompany(wh1.getId(), "Smoow");
        brands.add(smoow);
        ProductCompany boou = new ProductCompany(wh1.getId(), "Boou");
        brands.add(boou);
        brands.add(new ProductCompany(wh1.getId(),"Miraj"));
        brands.add(new ProductCompany(wh1.getId(),"Vek"));
        brands.add(new ProductCompany(wh1.getId(),"Nova"));
        brands.add(new ProductCompany(wh1.getId(),"Kalori"));
        brands.add(new ProductCompany(wh1.getId(),"Valensiya"));
        brands.add(new ProductCompany(wh1.getId(),"Brimix"));
        brands.add(new ProductCompany(wh1.getId(),"Agua"));
        brands.add(new ProductCompany(wh1.getId(),"Huadiado"));
        brands.add(new ProductCompany(wh1.getId(),"Fauset"));
        ProductCompany mebel = new ProductCompany(wh1.getId(), "Mebel");
        brands.add(mebel);
        brandRepository.saveAll(brands);

        List<Product> products = new ArrayList<>();

        products.add(new Product("170x70 primoy chugun vanna Smoow",smoow.getId() , 10d));
        products.add(new Product("170x75 ruchkali chugun vanna Smoow",smoow.getId() , 10d));
        products.add(new Product("160x75 figurniy bez ruchka chugun vanna Smoow",smoow.getId() , 10d));
        products.add(new Product("160x70 chugun vanna Smoow",smoow.getId() , 10d));
        products.add(new Product("150x75 ruchkali chugun vanna Smoow",smoow.getId() , 10d));
        products.add(new Product("150x75 figurni bez ruchka chugun vanna Smoow",smoow.getId() , 10d));
        products.add(new Product("150x70 restavratsiya chugun vanna Smoow",smoow.getId() , 10d));
        products.add(new Product("140x70 chugun vanna Smoow",smoow.getId() , 10d));
        products.add(new Product("130x70 chugun vanna Smoow",smoow.getId() , 10d));
        products.add(new Product("120x70 chugun vanna Smoow",smoow.getId() , 10d));
        products.add(new Product("120x70  restavratsiya chugun vanna Smoow",smoow.getId() , 10d));
        products.add(new Product("180x80 siri ko'chgan ruchkali chugun vanna Smoow",smoow.getId() , 10d));
        products.add(new Product("170x75 defektli ruchkali chugun vanna Smoow",smoow.getId() , 10d));
        products.add(new Product("170x70 svarka sachragan primoy chugun vanna Smoow",smoow.getId() , 10d));
        products.add(new Product("150x75 sirri ko'chgan ruchkali chugun vanna Smoow",smoow.getId() , 10d));
        products.add(new Product("150x75 defektli bez ruchka chugun vanna Smoow",smoow.getId() , 10d));
        products.add(new Product("140x70 svarka sachragan chugun vanna Smoow",smoow.getId() , 10d));

        products.add(new Product("8191-55 dush smesitel BOOU",boou.getId() , 10d));
        products.add(new Product("8276-18f dush smesitel BOOU",boou.getId() , 10d));
        products.add(new Product("91012-5 dush smesitel BOOU",boou.getId() , 10d));
        products.add(new Product("8140-5 dush smesitel BOOU",boou.getId() , 10d));
        products.add(new Product("8213-5f dush smesitel boou",boou.getId() , 10d));
        products.add(new Product("91003-5 dush smesitel boou",boou.getId() , 10d));
        products.add(new Product("91011-16 dush smesitel boou",boou.getId() , 10d));
        products.add(new Product("91011-05 dush smesitel boou",boou.getId() , 10d));
        products.add(new Product("8213 pv 46 oq dush smesitel boou",boou.getId() , 10d));
        products.add(new Product("8213 46f dush smesitel boou",boou.getId() , 10d));
        products.add(new Product("8174-10f kuxn smesitel boou",boou.getId() , 10d));
        products.add(new Product("8246-10f kuxn smesitel boou",boou.getId() , 10d));
        products.add(new Product("5330 kuxn smesitel boou",boou.getId() , 10d));

        products.add(new Product("Akvaton mebel",mebel.getId() , 10d));
        products.add(new Product("uyut 450 mebel ",mebel.getId() , 10d));
        products.add(new Product("Gratsiya mebel ",mebel.getId() , 10d));
        products.add(new Product("0113 rakvina mebel Ekokerama ",mebel.getId() , 10d));
        products.add(new Product("0112 rakvina mebel Ekokerama ",mebel.getId() , 10d));
        products.add(new Product("60 lik Cac mebel",mebel.getId() , 10d));
        products.add(new Product("60 akril mebel Cac",mebel.getId() , 10d));
        products.add(new Product("60 oyna Mebel ",mebel.getId() , 10d));
        products.add(new Product("50 oyna Mebel",mebel.getId() , 10d));
        products.add(new Product("70 oyna Mebel ",mebel.getId() , 10d));

        productRepository.saveAll(products);

    }

}
