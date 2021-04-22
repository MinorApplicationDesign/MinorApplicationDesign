package nl.bioinf.minorapplicationdesign.ontpillen.model.web_scraping;

import nl.bioinf.minorapplicationdesign.ontpillen.model.data_storage.DrugDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

/**
 *
 * @author Jonathan Klimp
 */

@SpringBootTest
class FarmacoWebScraperTest {

    @Autowired
    DrugDao drugDao;

    @Autowired
    FarmacoWebScraper farmacoWebScraper;

    @Autowired
    DrugFetcher drugFetcher;

    @BeforeEach
    public void storeDrugs() throws IOException {
        SSLHelper.bypassSSL();
        drugFetcher.parseHtml();
    }

    @Test
    void parseHtml() throws IOException {
        String[] drugSubstancesArray = {"alprazolam", "oxazepam", "nalmefeen", "quetiapine", "pimozide", "flurazepam", "zuclopentixol", "fenelzine", "lithium", "mianserine", "buprenorfine (bij verslaving)", "nitrazepam", "amisulpride", "clobazam", "dapoxetine", "midazolam", "chloorprotixeen", "buprenorfine/naloxon", "aripiprazol", "citalopram", "dosulepine", "sertraline", "methylfenidaat", "nortriptyline", "clomipramine", "temazepam", "nicotine", "loprazolam", "prazepam", "clorazepinezuur", "modafinil", "lorazepam", "broomperidol", "fluspirileen", "lormetazepam", "zolpidem", "flupentixol", "melatonine", "tranylcypromine", "clozapine", "dexamfetamine", "buspiron", "bupropion", "methadon", "risperidon", "diazepam", "vortioxetine", "coffeïne", "disulfiram", "brotizolam", "atomoxetine", "lisdexamfetamine", "zopiclon", "paliperidon", "acamprosaat", "varenicline", "olanzapine", "haloperidol", "venlafaxine", "fluoxetine", "bromazepam", "penfluridol", "trazodon", "pipamperon", "periciazine", "escitalopram", "moclobemide", "sulpiride", "sertindol", "amitriptyline", "mirtazapine", "imipramine", "maprotiline", "naltrexon", "fluvoxamine", "agomelatine", "lurasidon", "paroxetine", "esketamine (nasaal)", "duloxetine", "cariprazine", "flunitrazepam", "brexpiprazol"};

        farmacoWebScraper.parseHtml();
    }
}