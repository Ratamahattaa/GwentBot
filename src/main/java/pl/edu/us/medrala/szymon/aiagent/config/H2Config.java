package pl.edu.us.medrala.szymon.aiagent.config;

import lombok.extern.slf4j.Slf4j;
import org.h2.tools.Server;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.edu.us.medrala.szymon.aiagent.cards.Ability;
import pl.edu.us.medrala.szymon.aiagent.cards.Card;
import pl.edu.us.medrala.szymon.aiagent.cards.CardsLoader;
import pl.edu.us.medrala.szymon.aiagent.cards.Fraction;
import pl.edu.us.medrala.szymon.aiagent.cards.Leader;
import pl.edu.us.medrala.szymon.aiagent.cards.Range;
import pl.edu.us.medrala.szymon.aiagent.cards.Type;
import pl.edu.us.medrala.szymon.aiagent.cards.repository.CardRepo;
import pl.edu.us.medrala.szymon.aiagent.cards.repository.LeaderRepo;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Configuration
class H2Config {
    @Bean(initMethod = "start", destroyMethod = "stop")
    Server h2Server() throws SQLException {
        return Server.createTcpServer("-tcp");
    }

    @Bean
    CommandLineRunner saveNRLeaders(final LeaderRepo leaderRepo) {
        return args -> {
            Leader theSteelForged = new Leader();
            theSteelForged.setId(31L);
            theSteelForged.setName("The Steel-Forged");
            theSteelForged.setPrice(60);
            theSteelForged.setAbility(Ability.Leader.THE_STEEL_FORGED);
            theSteelForged.setFraction(Fraction.NORTHERN_REALMS);
            theSteelForged.setImage(CardsLoader.getImageBytes("The Steel-Forged.png"));

            Leader theSiegemaster = new Leader();
            theSiegemaster.setId(39L);
            theSiegemaster.setName("The Siegemaster");
            theSiegemaster.setPrice(55);
            theSiegemaster.setAbility(Ability.Leader.THE_SIEGEMASTER);
            theSiegemaster.setFraction(Fraction.NORTHERN_REALMS);
            theSiegemaster.setImage(CardsLoader.getImageBytes("The Siegemaster.png"));

            Leader lordCommanderOfTheNorth = new Leader();
            lordCommanderOfTheNorth.setId(47L);
            lordCommanderOfTheNorth.setName("Lord Commander of the North");
            lordCommanderOfTheNorth.setPrice(50);
            lordCommanderOfTheNorth.setAbility(Ability.Leader.LORD_COMMANDER_OF_THE_NORTH);
            lordCommanderOfTheNorth.setFraction(Fraction.NORTHERN_REALMS);
            lordCommanderOfTheNorth.setImage(CardsLoader.getImageBytes("Lord Commander of the North.png"));

            Leader kingOfTemeria = new Leader();
            kingOfTemeria.setId(55L);
            kingOfTemeria.setName("King of Temeria");
            kingOfTemeria.setPrice(45);
            kingOfTemeria.setAbility(Ability.Leader.KING_OF_TEMERIA);
            kingOfTemeria.setFraction(Fraction.NORTHERN_REALMS);
            kingOfTemeria.setImage(CardsLoader.getImageBytes("King of Temeria.png"));

            List<Leader> entities = Arrays.asList(theSteelForged, theSiegemaster, lordCommanderOfTheNorth, kingOfTemeria);
            entities.forEach(e -> {
                if (e.getId() == null || e.getId() == 0L) {
                    log.info("ERROR CARD: {}", e);
                    throw new RuntimeException(e.toString());
                }
            });
            leaderRepo.saveAll(entities);
        };
    }

    @Bean
    CommandLineRunner saveNGLeaders(LeaderRepo leaderRepo) {
        return args -> {
            Leader theRelentless = new Leader();
            theRelentless.setId(70L);
            theRelentless.setName("The Relentless");
            theRelentless.setPrice(60);
            theRelentless.setAbility(Ability.Leader.THE_RELENTLESS);
            theRelentless.setFraction(Fraction.NILGAARD);
            theRelentless.setImage(CardsLoader.getImageBytes("The Relentless.png"));

            Leader theWhiteFlame = new Leader();
            theWhiteFlame.setId(78L);
            theWhiteFlame.setName("The White Flame");
            theWhiteFlame.setPrice(55);
            theWhiteFlame.setAbility(Ability.Leader.THE_WHITE_FLAME);
            theWhiteFlame.setFraction(Fraction.NILGAARD);
            theWhiteFlame.setImage(CardsLoader.getImageBytes("The White Flame.png"));

            Leader theEmperorOfNilfgaard = new Leader();
            theEmperorOfNilfgaard.setId(86L);
            theEmperorOfNilfgaard.setName("The Emperor of Nilfgaard");
            theEmperorOfNilfgaard.setPrice(50);
            theEmperorOfNilfgaard.setAbility(Ability.Leader.THE_EMPEROR_OF_NILFGAARD);
            theEmperorOfNilfgaard.setFraction(Fraction.NILGAARD);
            theEmperorOfNilfgaard.setImage(CardsLoader.getImageBytes("The Emperor of Nilfgaard.png"));

            Leader hisImperialMajesty = new Leader();
            hisImperialMajesty.setId(94L);
            hisImperialMajesty.setName("His Imperial Majesty");
            hisImperialMajesty.setPrice(45);
            hisImperialMajesty.setAbility(Ability.Leader.HIS_IMPERIAL_MAJESTY);
            hisImperialMajesty.setFraction(Fraction.NILGAARD);
            hisImperialMajesty.setImage(CardsLoader.getImageBytes("His Imperial Majesty.png"));


            List<Leader> entities = Arrays.asList(theRelentless, theWhiteFlame, theEmperorOfNilfgaard, hisImperialMajesty);
            entities.forEach(e -> {
                if (e.getId() == null || e.getId() == 0L) {
                    log.info("ERROR CARD: {}", e);
                    throw new RuntimeException(e.toString());
                }
            });
            leaderRepo.saveAll(entities);
        };
    }

    @Bean
    CommandLineRunner saveSTLeaders(LeaderRepo leaderRepo) {
        return args -> {
            Leader queenOfDolBlathanna = new Leader();
            queenOfDolBlathanna.setId(109L);
            queenOfDolBlathanna.setName("Queen of Dol Blathanna");
            queenOfDolBlathanna.setPrice(60);
            queenOfDolBlathanna.setAbility(Ability.Leader.QUEEN_OF_DOL_BLATHANNA);
            queenOfDolBlathanna.setFraction(Fraction.SCOIATAEL);
            queenOfDolBlathanna.setImage(CardsLoader.getImageBytes("Queen of Dol Blathanna.png"));

            Leader theBeautiful = new Leader();
            theBeautiful.setId(117L);
            theBeautiful.setName("The Beautiful");
            theBeautiful.setPrice(55);
            theBeautiful.setAbility(Ability.Leader.THE_BEAUTIFUL);
            theBeautiful.setFraction(Fraction.SCOIATAEL);
            theBeautiful.setImage(CardsLoader.getImageBytes("The Beautiful.png"));

            Leader daisyOfTheValley = new Leader();
            daisyOfTheValley.setId(125L);
            daisyOfTheValley.setName("Daisy of the Valley");
            daisyOfTheValley.setPrice(50);
            daisyOfTheValley.setAbility(Ability.Leader.DAISY_OF_THE_VALLEY);
            daisyOfTheValley.setFraction(Fraction.SCOIATAEL);
            daisyOfTheValley.setImage(CardsLoader.getImageBytes("Daisy of the Valley.png"));

            Leader purebloodElf = new Leader();
            purebloodElf.setId(133L);
            purebloodElf.setName("Pureblood Elf");
            purebloodElf.setPrice(45);
            purebloodElf.setAbility(Ability.Leader.PUREBLOOD_ELF);
            purebloodElf.setFraction(Fraction.SCOIATAEL);
            purebloodElf.setImage(CardsLoader.getImageBytes("Pureblood Elf.png"));

            List<Leader> entities = Arrays.asList(queenOfDolBlathanna, theBeautiful, daisyOfTheValley, purebloodElf);
            entities.forEach(e -> {
                if (e.getId() == null || e.getId() == 0L) {
                    log.info("ERROR CARD: {}", e);
                    throw new RuntimeException(e.toString());
                }
            });
            leaderRepo.saveAll(entities);
        };
    }

    @Bean
    CommandLineRunner saveMOLeaders(LeaderRepo leaderRepo) {
        return args -> {
            Leader destroyerOfWorlds = new Leader();
            destroyerOfWorlds.setId(148L);
            destroyerOfWorlds.setName("Destroyer of Worlds");
            destroyerOfWorlds.setPrice(60);
            destroyerOfWorlds.setAbility(Ability.Leader.DESTROYER_OF_WORLDS);
            destroyerOfWorlds.setFraction(Fraction.MONSTERS);
            destroyerOfWorlds.setImage(CardsLoader.getImageBytes("Destroyer of Worlds.png"));

            Leader bringerOfDeath = new Leader();
            bringerOfDeath.setId(156L);
            bringerOfDeath.setName("Bringer of Death");
            bringerOfDeath.setPrice(55);
            bringerOfDeath.setAbility(Ability.Leader.BRINGER_OF_DEATH);
            bringerOfDeath.setFraction(Fraction.MONSTERS);
            bringerOfDeath.setImage(CardsLoader.getImageBytes("Bringer of Death.png"));

            Leader kingOfTheWildHunt = new Leader();
            kingOfTheWildHunt.setId(164L);
            kingOfTheWildHunt.setName("King of the Wild Hunt");
            kingOfTheWildHunt.setPrice(50);
            kingOfTheWildHunt.setAbility(Ability.Leader.KING_OF_THE_WILD_HUNT);
            kingOfTheWildHunt.setFraction(Fraction.MONSTERS);
            kingOfTheWildHunt.setImage(CardsLoader.getImageBytes("King of the Wild Hunt.png"));

            Leader commanderOfTheRedRiders = new Leader();
            commanderOfTheRedRiders.setId(172L);
            commanderOfTheRedRiders.setName("Commander of the Red Riders");
            commanderOfTheRedRiders.setPrice(45);
            commanderOfTheRedRiders.setAbility(Ability.Leader.COMMANDER_OF_THE_RED_RIDERS);
            commanderOfTheRedRiders.setFraction(Fraction.MONSTERS);
            commanderOfTheRedRiders.setImage(CardsLoader.getImageBytes("Commander of the Red Riders.png"));

            List<Leader> entities = Arrays.asList(destroyerOfWorlds, bringerOfDeath, kingOfTheWildHunt, commanderOfTheRedRiders);
            entities.forEach(e -> {
                if (e.getId() == null || e.getId() == 0L) {
                    log.info("ERROR CARD: {}", e);
                    throw new RuntimeException(e.toString());
                }
            });
            leaderRepo.saveAll(entities);
        };
    }

    @Bean
    CommandLineRunner saveNeutralCards(CardRepo cardRepo) {
        return args -> {
            Card roach = new Card(3);
            roach.setId(187L);
            roach.setName("Roach");
            roach.setUnitStrength(3);
            roach.setAbility(Ability.NONE);
            roach.setType(Type.NONE);
            roach.setPrice(50);
            roach.setRange(Range.CLOSE_COMBAT);
            roach.setFraction(Fraction.NEUTRAL);
            roach.setImage(CardsLoader.getImageBytes("Roach.jpg"));

            Card geraltOfRivia = new Card(15);
            geraltOfRivia.setId(198L);
            geraltOfRivia.setName("Geralt of Rivia");
            geraltOfRivia.setUnitStrength(15);
            geraltOfRivia.setAbility(Ability.MUSTER);
            geraltOfRivia.setType(Type.HERO);
            geraltOfRivia.setPrice(50);
            geraltOfRivia.setRange(Range.CLOSE_COMBAT);
            geraltOfRivia.setFraction(Fraction.NEUTRAL);
            geraltOfRivia.setImage(CardsLoader.getImageBytes("Geralt of Rivia.png"));

            Card cirillaFionaElenRianno = new Card(15);
            cirillaFionaElenRianno.setId(209L);
            cirillaFionaElenRianno.setName("Cirilla Fiona Elen Rianno");
            cirillaFionaElenRianno.setUnitStrength(15);
            cirillaFionaElenRianno.setAbility(Ability.MUSTER);
            cirillaFionaElenRianno.setType(Type.HERO);
            cirillaFionaElenRianno.setPrice(50);
            cirillaFionaElenRianno.setRange(Range.CLOSE_COMBAT);
            cirillaFionaElenRianno.setFraction(Fraction.NEUTRAL);
            cirillaFionaElenRianno.setImage(CardsLoader.getImageBytes("Cirilla Fiona Elen Rianno.png"));

            Card vesemir = new Card(6);
            vesemir.setId(220L);
            vesemir.setName("Vesemir");
            vesemir.setUnitStrength(6);
            vesemir.setAbility(Ability.HERO);
            vesemir.setType(Type.NONE);
            vesemir.setPrice(20);
            vesemir.setRange(Range.CLOSE_COMBAT);
            vesemir.setFraction(Fraction.NEUTRAL);
            vesemir.setImage(CardsLoader.getImageBytes("Vesemir.png"));

            Card yenneferOfVengerberg = new Card(7);
            yenneferOfVengerberg.setId(231L);
            yenneferOfVengerberg.setName("Yennefer of Vengerberg");
            yenneferOfVengerberg.setUnitStrength(7);
            yenneferOfVengerberg.setAbility(Ability.MEDIC);
            yenneferOfVengerberg.setType(Type.HERO);
            yenneferOfVengerberg.setPrice(50);
            yenneferOfVengerberg.setRange(Range.RANGED);
            yenneferOfVengerberg.setFraction(Fraction.NEUTRAL);
            yenneferOfVengerberg.setImage(CardsLoader.getImageBytes("Yennefer of Vengerberg.png"));

            Card trissMerigold = new Card(7);
            trissMerigold.setId(242L);
            trissMerigold.setName("Triss Merigold");
            trissMerigold.setUnitStrength(7);
            trissMerigold.setAbility(Ability.NONE);
            trissMerigold.setType(Type.HERO);
            trissMerigold.setPrice(20);
            trissMerigold.setRange(Range.CLOSE_COMBAT);
            trissMerigold.setFraction(Fraction.NEUTRAL);
            trissMerigold.setImage(CardsLoader.getImageBytes("Triss Merigold.png"));

            Card dandelion = new Card(2);
            dandelion.setId(253L);
            dandelion.setName("Dandelion");
            dandelion.setUnitStrength(2);
            dandelion.setAbility(Ability.COMMANDERS_HORN);
            dandelion.setType(Type.NONE);
            dandelion.setPrice(20);
            dandelion.setRange(Range.CLOSE_COMBAT);
            dandelion.setFraction(Fraction.NEUTRAL);
            dandelion.setImage(CardsLoader.getImageBytes("Dandelion.png"));

            Card zoltanChivay = new Card(5);
            zoltanChivay.setId(264L);
            zoltanChivay.setName("Zoltan Chivay");
            zoltanChivay.setUnitStrength(5);
            zoltanChivay.setAbility(Ability.NONE);
            zoltanChivay.setType(Type.NONE);
            zoltanChivay.setPrice(10);
            zoltanChivay.setRange(Range.CLOSE_COMBAT);
            zoltanChivay.setFraction(Fraction.NEUTRAL);
            zoltanChivay.setImage(CardsLoader.getImageBytes("Zoltan Chivay.png"));

            Card emielRegisRohellecTerzieff = new Card(5);
            emielRegisRohellecTerzieff.setId(275L);
            emielRegisRohellecTerzieff.setName("Emiel Regis Rohellec Terzieff");
            emielRegisRohellecTerzieff.setUnitStrength(5);
            emielRegisRohellecTerzieff.setAbility(Ability.NONE);
            emielRegisRohellecTerzieff.setType(Type.NONE);
            emielRegisRohellecTerzieff.setPrice(10);
            emielRegisRohellecTerzieff.setRange(Range.CLOSE_COMBAT);
            emielRegisRohellecTerzieff.setFraction(Fraction.NEUTRAL);
            emielRegisRohellecTerzieff.setImage(CardsLoader.getImageBytes("Emiel Regis Rohellec Terzieff.png"));


            Card villentretenmerth = new Card(7);
            villentretenmerth.setId(287L);
            villentretenmerth.setName("Villentretenmerth");
            villentretenmerth.setUnitStrength(7);
            villentretenmerth.setAbility(Ability.SCORCH);
            villentretenmerth.setType(Type.NONE);
            villentretenmerth.setPrice(10);
            villentretenmerth.setRange(Range.CLOSE_COMBAT);
            villentretenmerth.setFraction(Fraction.NEUTRAL);
            villentretenmerth.setImage(CardsLoader.getImageBytes("Villentretenmerth.png"));


            Card avallach = new Card(0);
            avallach.setId(299L);
            avallach.setName("Avallac’h");
            avallach.setUnitStrength(0);
            avallach.setAbility(Ability.SPY);
            avallach.setType(Type.HERO);
            avallach.setPrice(20);
            avallach.setRange(Range.CLOSE_COMBAT);
            avallach.setFraction(Fraction.NEUTRAL);
            avallach.setImage(CardsLoader.getImageBytes("Avallach.png"));

            Card firstDecoy = new Card(0);
            firstDecoy.setId(310L);
            firstDecoy.setName("Decoy");
            firstDecoy.setUnitStrength(0);
            firstDecoy.setAbility(Ability.NONE);
            firstDecoy.setType(Type.NONE);
            firstDecoy.setPrice(20);
            firstDecoy.setRange(Range.NONE);
            firstDecoy.setFraction(Fraction.NEUTRAL);
            firstDecoy.setImage(CardsLoader.getImageBytes("Decoy.png"));

            Card secondDecoy = new Card(0);
            secondDecoy.setId(321L);
            secondDecoy.setName("Decoy");
            secondDecoy.setUnitStrength(0);
            secondDecoy.setAbility(Ability.NONE);
            secondDecoy.setType(Type.NONE);
            secondDecoy.setPrice(20);
            secondDecoy.setRange(Range.NONE);
            secondDecoy.setFraction(Fraction.NEUTRAL);
            secondDecoy.setImage(CardsLoader.getImageBytes("Decoy.png"));

            Card thirdDecoy = new Card(0);
            thirdDecoy.setId(332L);
            thirdDecoy.setName("Decoy");
            thirdDecoy.setUnitStrength(0);
            thirdDecoy.setAbility(Ability.NONE);
            thirdDecoy.setType(Type.NONE);
            thirdDecoy.setPrice(50);
            thirdDecoy.setRange(Range.NONE);
            thirdDecoy.setFraction(Fraction.NEUTRAL);
            thirdDecoy.setImage(CardsLoader.getImageBytes("Decoy.png"));

            Card firstCommandersHorn = new Card(0);
            firstCommandersHorn.setId(343L);
            firstCommandersHorn.setName("Commander’s Horn");
            firstCommandersHorn.setUnitStrength(0);
            firstCommandersHorn.setAbility(Ability.COMMANDERS_HORN);
            firstCommandersHorn.setType(Type.NONE);
            firstCommandersHorn.setPrice(10);
            firstCommandersHorn.setRange(Range.ANY);
            firstCommandersHorn.setFraction(Fraction.NEUTRAL);
            firstCommandersHorn.setImage(CardsLoader.getImageBytes("Commanders Horn.png"));

            Card secondCommandersHorn = new Card(0);
            secondCommandersHorn.setId(354L);
            secondCommandersHorn.setName("Commander’s Horn");
            secondCommandersHorn.setUnitStrength(0);
            secondCommandersHorn.setAbility(Ability.COMMANDERS_HORN);
            secondCommandersHorn.setType(Type.NONE);
            secondCommandersHorn.setPrice(10);
            secondCommandersHorn.setRange(Range.ANY);
            secondCommandersHorn.setFraction(Fraction.NEUTRAL);
            secondCommandersHorn.setImage(CardsLoader.getImageBytes("Commanders Horn.png"));

            Card thirdCommandersHorn = new Card(0);
            thirdCommandersHorn.setId(365L);
            thirdCommandersHorn.setName("Commander’s Horn");
            thirdCommandersHorn.setUnitStrength(0);
            thirdCommandersHorn.setAbility(Ability.COMMANDERS_HORN);
            thirdCommandersHorn.setType(Type.NONE);
            thirdCommandersHorn.setPrice(20);
            thirdCommandersHorn.setRange(Range.ANY);
            thirdCommandersHorn.setFraction(Fraction.NEUTRAL);
            thirdCommandersHorn.setImage(CardsLoader.getImageBytes("Commanders Horn.png"));

            Card firstScorch = new Card(0);
            firstScorch.setId(376L);
            firstScorch.setName("Scorch");
            firstScorch.setUnitStrength(0);
            firstScorch.setAbility(Ability.NONE);
            firstScorch.setType(Type.NONE);
            firstScorch.setPrice(50);
            firstScorch.setRange(Range.NONE);
            firstScorch.setFraction(Fraction.NEUTRAL);
            firstScorch.setImage(CardsLoader.getImageBytes("Scorch.png"));

            Card secondScorch = new Card(0);
            secondScorch.setId(387L);
            secondScorch.setName("Scorch");
            secondScorch.setUnitStrength(0);
            secondScorch.setAbility(Ability.NONE);
            secondScorch.setType(Type.NONE);
            secondScorch.setPrice(50);
            secondScorch.setRange(Range.NONE);
            secondScorch.setFraction(Fraction.NEUTRAL);
            secondScorch.setImage(CardsLoader.getImageBytes("Scorch.png"));


            Card thirdScorch = new Card(0);
            thirdScorch.setId(399L);
            thirdScorch.setName("Scorch");
            thirdScorch.setUnitStrength(0);
            thirdScorch.setAbility(Ability.NONE);
            thirdScorch.setType(Type.NONE);
            thirdScorch.setPrice(50);
            thirdScorch.setRange(Range.NONE);
            thirdScorch.setFraction(Fraction.NEUTRAL);
            thirdScorch.setImage(CardsLoader.getImageBytes("Scorch.png"));

            Card firstBitingFrost = new Card(0);
            firstBitingFrost.setId(410L);
            firstBitingFrost.setName("Biting Frost");
            firstBitingFrost.setUnitStrength(0);
            firstBitingFrost.setAbility(Ability.NONE);
            firstBitingFrost.setType(Type.NONE);
            firstBitingFrost.setPrice(0);
            firstBitingFrost.setRange(Range.CLOSE_COMBAT);
            firstBitingFrost.setFraction(Fraction.NEUTRAL);
            firstBitingFrost.setImage(CardsLoader.getImageBytes("Biting Frost.png"));

            Card secondBitingFrost = new Card(0);
            secondBitingFrost.setId(421L);
            secondBitingFrost.setName("Biting Frost");
            secondBitingFrost.setUnitStrength(0);
            secondBitingFrost.setAbility(Ability.NONE);
            secondBitingFrost.setType(Type.NONE);
            secondBitingFrost.setPrice(0);
            secondBitingFrost.setRange(Range.CLOSE_COMBAT);
            secondBitingFrost.setFraction(Fraction.NEUTRAL);
            secondBitingFrost.setImage(CardsLoader.getImageBytes("Biting Frost.png"));


            Card thirdBitingFrost = new Card(0);
            thirdBitingFrost.setId(433L);
            thirdBitingFrost.setName("Biting Frost");
            thirdBitingFrost.setUnitStrength(0);
            thirdBitingFrost.setAbility(Ability.NONE);
            thirdBitingFrost.setType(Type.NONE);
            thirdBitingFrost.setPrice(20);
            thirdBitingFrost.setRange(Range.CLOSE_COMBAT);
            thirdBitingFrost.setFraction(Fraction.NEUTRAL);
            thirdBitingFrost.setImage(CardsLoader.getImageBytes("Biting Frost.png"));

            Card firstImpenetrableFog = new Card(0);
            firstImpenetrableFog.setId(444L);
            firstImpenetrableFog.setName("Impenetrable Fog");
            firstImpenetrableFog.setUnitStrength(0);
            firstImpenetrableFog.setAbility(Ability.NONE);
            firstImpenetrableFog.setType(Type.NONE);
            firstImpenetrableFog.setPrice(0);
            firstImpenetrableFog.setRange(Range.RANGED);
            firstImpenetrableFog.setFraction(Fraction.NEUTRAL);
            firstImpenetrableFog.setImage(CardsLoader.getImageBytes("Impenetrable Fog.png"));

            Card secondImpenetrableFog = new Card(0);
            secondImpenetrableFog.setId(455L);
            secondImpenetrableFog.setName("Impenetrable Fog");
            secondImpenetrableFog.setUnitStrength(0);
            secondImpenetrableFog.setAbility(Ability.NONE);
            secondImpenetrableFog.setType(Type.NONE);
            secondImpenetrableFog.setPrice(0);
            secondImpenetrableFog.setRange(Range.RANGED);
            secondImpenetrableFog.setFraction(Fraction.NEUTRAL);
            secondImpenetrableFog.setImage(CardsLoader.getImageBytes("Impenetrable Fog.png"));

            Card thirdImpenetrableFog = new Card(0);
            thirdImpenetrableFog.setId(466L);
            thirdImpenetrableFog.setName("Impenetrable Fog");
            thirdImpenetrableFog.setUnitStrength(0);
            thirdImpenetrableFog.setAbility(Ability.NONE);
            thirdImpenetrableFog.setType(Type.NONE);
            thirdImpenetrableFog.setPrice(20);
            thirdImpenetrableFog.setRange(Range.RANGED);
            thirdImpenetrableFog.setFraction(Fraction.NEUTRAL);
            thirdImpenetrableFog.setImage(CardsLoader.getImageBytes("Impenetrable Fog.png"));

            Card firstTorrentialRain = new Card(0);
            firstTorrentialRain.setId(477L);
            firstTorrentialRain.setName("Torrential Rain");
            firstTorrentialRain.setUnitStrength(0);
            firstTorrentialRain.setAbility(Ability.NONE);
            firstTorrentialRain.setType(Type.NONE);
            firstTorrentialRain.setPrice(0);
            firstTorrentialRain.setRange(Range.SIEGE);
            firstTorrentialRain.setFraction(Fraction.NEUTRAL);
            firstTorrentialRain.setImage(CardsLoader.getImageBytes("Torrential Rain.png"));

            Card secondTorrentialRain = new Card(0);
            secondTorrentialRain.setId(488L);
            secondTorrentialRain.setName("Torrential Rain");
            secondTorrentialRain.setUnitStrength(0);
            secondTorrentialRain.setAbility(Ability.NONE);
            secondTorrentialRain.setType(Type.NONE);
            secondTorrentialRain.setPrice(0);
            secondTorrentialRain.setRange(Range.SIEGE);
            secondTorrentialRain.setFraction(Fraction.NEUTRAL);
            secondTorrentialRain.setImage(CardsLoader.getImageBytes("Torrential Rain.png"));

            Card thirdTorrentialRain = new Card(0);
            thirdTorrentialRain.setId(499L);
            thirdTorrentialRain.setName("Torrential Rain");
            thirdTorrentialRain.setUnitStrength(0);
            thirdTorrentialRain.setAbility(Ability.NONE);
            thirdTorrentialRain.setType(Type.NONE);
            thirdTorrentialRain.setPrice(20);
            thirdTorrentialRain.setRange(Range.SIEGE);
            thirdTorrentialRain.setFraction(Fraction.NEUTRAL);
            thirdTorrentialRain.setImage(CardsLoader.getImageBytes("Torrential Rain.png"));

            Card firstClearWeather = new Card(0);
            firstClearWeather.setId(510L);
            firstClearWeather.setName("Clear Weather");
            firstClearWeather.setUnitStrength(0);
            firstClearWeather.setAbility(Ability.NONE);
            firstClearWeather.setType(Type.NONE);
            firstClearWeather.setPrice(0);
            firstClearWeather.setRange(Range.NONE);
            firstClearWeather.setFraction(Fraction.NEUTRAL);
            firstClearWeather.setImage(CardsLoader.getImageBytes("Clear Weather.png"));

            Card secondClearWeather = new Card(0);
            secondClearWeather.setId(521L);
            secondClearWeather.setName("Clear Weather");
            secondClearWeather.setUnitStrength(0);
            secondClearWeather.setAbility(Ability.NONE);
            secondClearWeather.setType(Type.NONE);
            secondClearWeather.setPrice(0);
            firstClearWeather.setRange(Range.NONE);
            firstClearWeather.setFraction(Fraction.NEUTRAL);
            firstClearWeather.setImage(CardsLoader.getImageBytes("Clear Weather.png"));

            Card thirdClearWeather = new Card(0);
            thirdClearWeather.setId(532L);
            thirdClearWeather.setName("Clear Weather");
            thirdClearWeather.setUnitStrength(0);
            thirdClearWeather.setAbility(Ability.NONE);
            thirdClearWeather.setType(Type.NONE);
            thirdClearWeather.setPrice(20);
            firstClearWeather.setRange(Range.NONE);
            firstClearWeather.setFraction(Fraction.NEUTRAL);
            firstClearWeather.setImage(CardsLoader.getImageBytes("Clear Weather.png"));


            List<Card> entities = Arrays.asList(
                    roach, geraltOfRivia, cirillaFionaElenRianno, vesemir, yenneferOfVengerberg, trissMerigold,
                    dandelion, zoltanChivay, emielRegisRohellecTerzieff, villentretenmerth, avallach, firstDecoy,
                    secondDecoy, thirdDecoy, firstCommandersHorn, secondCommandersHorn, thirdCommandersHorn,
                    firstScorch, secondScorch, thirdScorch, firstBitingFrost, secondBitingFrost, thirdBitingFrost,
                    firstImpenetrableFog, secondImpenetrableFog, thirdImpenetrableFog, firstTorrentialRain,
                    secondTorrentialRain, thirdTorrentialRain, firstClearWeather, secondClearWeather, thirdClearWeather);

            entities.forEach(e -> {
                if (e.getId() == null || e.getId() == 0L) {
                    log.info("ERROR CARD: {}", e);
                    throw new RuntimeException(e.toString());
                }
            });

            cardRepo.saveAll(entities);
        };
    }

    @Bean
    CommandLineRunner saveNRCards(CardRepo cardRepo) {
        return args -> {
            Card vernonRoche = new Card(10);
            vernonRoche.setId(557L);
            vernonRoche.setName("Vernon Roche");
            vernonRoche.setUnitStrength(10);
            vernonRoche.setAbility(Ability.NONE);
            vernonRoche.setType(Type.HERO);
            vernonRoche.setPrice(50);
            vernonRoche.setRange(Range.CLOSE_COMBAT);
            vernonRoche.setFraction(Fraction.NORTHERN_REALMS);
            vernonRoche.setImage(CardsLoader.getImageBytes("Vernon Roche.png"));

            Card johnNatalis = new Card(10);
            johnNatalis.setId(568L);
            johnNatalis.setName("John Natalis");
            johnNatalis.setUnitStrength(10);
            johnNatalis.setAbility(Ability.NONE);
            johnNatalis.setType(Type.HERO);
            johnNatalis.setPrice(50);
            johnNatalis.setRange(Range.CLOSE_COMBAT);
            johnNatalis.setFraction(Fraction.NORTHERN_REALMS);
            johnNatalis.setImage(CardsLoader.getImageBytes("John Natalis.png"));

            Card esteradThyssen = new Card(10);
            esteradThyssen.setId(579L);
            esteradThyssen.setName("Esterad Thyssen");
            esteradThyssen.setUnitStrength(10);
            esteradThyssen.setAbility(Ability.NONE);
            esteradThyssen.setType(Type.HERO);
            esteradThyssen.setPrice(50);
            esteradThyssen.setRange(Range.CLOSE_COMBAT);
            esteradThyssen.setFraction(Fraction.NORTHERN_REALMS);
            esteradThyssen.setImage(CardsLoader.getImageBytes("Esterad Thyssen.png"));

            Card philippaEilhart = new Card(10);
            philippaEilhart.setId(590L);
            philippaEilhart.setName("Philippa Eilhart");
            philippaEilhart.setUnitStrength(10);
            philippaEilhart.setAbility(Ability.NONE);
            philippaEilhart.setType(Type.HERO);
            philippaEilhart.setPrice(50);
            philippaEilhart.setRange(Range.RANGED);
            philippaEilhart.setFraction(Fraction.NORTHERN_REALMS);
            philippaEilhart.setImage(CardsLoader.getImageBytes("Philippa Eilhart.png"));

            Card thaler = new Card(1);
            thaler.setId(601L);
            thaler.setName("Thaler");
            thaler.setUnitStrength(1);
            thaler.setAbility(Ability.SPY);
            thaler.setType(Type.NONE);
            thaler.setPrice(20);
            thaler.setRange(Range.SIEGE);
            thaler.setFraction(Fraction.NORTHERN_REALMS);
            thaler.setImage(CardsLoader.getImageBytes("Thaler.png"));

            Card ves = new Card(5);
            ves.setId(612L);
            ves.setName("Ves");
            ves.setUnitStrength(5);
            ves.setAbility(Ability.NONE);
            ves.setType(Type.NONE);
            ves.setPrice(10);
            ves.setRange(Range.CLOSE_COMBAT);
            ves.setFraction(Fraction.NORTHERN_REALMS);
            ves.setImage(CardsLoader.getImageBytes("Ves.png"));

            Card siegfriedOfDenesle = new Card(5);
            siegfriedOfDenesle.setId(623L);
            siegfriedOfDenesle.setName("Siegfried of Denesle");
            siegfriedOfDenesle.setUnitStrength(5);
            siegfriedOfDenesle.setAbility(Ability.NONE);
            siegfriedOfDenesle.setType(Type.NONE);
            siegfriedOfDenesle.setPrice(20);
            siegfriedOfDenesle.setRange(Range.CLOSE_COMBAT);
            siegfriedOfDenesle.setFraction(Fraction.NORTHERN_REALMS);
            siegfriedOfDenesle.setImage(CardsLoader.getImageBytes("Siegfried of Denesle.png"));

            Card yarpenZigrin = new Card(2);
            yarpenZigrin.setId(624L);
            yarpenZigrin.setName("Yarpen Zigrin");
            yarpenZigrin.setUnitStrength(2);
            yarpenZigrin.setAbility(Ability.NONE);
            yarpenZigrin.setType(Type.NONE);
            yarpenZigrin.setPrice(10);
            yarpenZigrin.setRange(Range.CLOSE_COMBAT);
            yarpenZigrin.setFraction(Fraction.NORTHERN_REALMS);
            yarpenZigrin.setImage(CardsLoader.getImageBytes("Yarpen Zigrin.png"));

            Card sigismundDijkstra = new Card(4);
            sigismundDijkstra.setId(645L);
            sigismundDijkstra.setName("Sigismund Dijkstra");
            sigismundDijkstra.setUnitStrength(4);
            sigismundDijkstra.setAbility(Ability.SPY);
            sigismundDijkstra.setType(Type.NONE);
            sigismundDijkstra.setPrice(20);
            sigismundDijkstra.setRange(Range.CLOSE_COMBAT);
            sigismundDijkstra.setFraction(Fraction.NORTHERN_REALMS);
            sigismundDijkstra.setImage(CardsLoader.getImageBytes("Sigismund Dijkstra.png"));

            Card keiraMetz = new Card(5);
            keiraMetz.setId(656L);
            keiraMetz.setName("Keira Metz");
            keiraMetz.setUnitStrength(5);
            keiraMetz.setAbility(Ability.NONE);
            keiraMetz.setType(Type.NONE);
            keiraMetz.setPrice(10);
            keiraMetz.setRange(Range.RANGED);
            keiraMetz.setFraction(Fraction.NORTHERN_REALMS);
            keiraMetz.setImage(CardsLoader.getImageBytes("Keira Metz.png"));

            Card sileDeTansarville = new Card(5);
            sileDeTansarville.setId(667L);
            sileDeTansarville.setName("Síle de Tansarville");
            sileDeTansarville.setUnitStrength(5);
            sileDeTansarville.setAbility(Ability.NONE);
            sileDeTansarville.setType(Type.NONE);
            sileDeTansarville.setPrice(10);
            sileDeTansarville.setRange(Range.RANGED);
            sileDeTansarville.setFraction(Fraction.NORTHERN_REALMS);
            sileDeTansarville.setImage(CardsLoader.getImageBytes("Síle de Tansarville.png"));

            Card sabrinaGlevissig = new Card(4);
            sabrinaGlevissig.setId(678L);
            sabrinaGlevissig.setName("Sabrina Glevissig");
            sabrinaGlevissig.setUnitStrength(4);
            sabrinaGlevissig.setAbility(Ability.NONE);
            sabrinaGlevissig.setType(Type.NONE);
            sabrinaGlevissig.setPrice(10);
            sabrinaGlevissig.setRange(Range.RANGED);
            sabrinaGlevissig.setFraction(Fraction.NORTHERN_REALMS);
            sabrinaGlevissig.setImage(CardsLoader.getImageBytes("Sabrina Glevissig.png"));

            Card sheldonSkaggs = new Card(4);
            sheldonSkaggs.setId(689L);
            sheldonSkaggs.setName("Sheldon Skaggs");
            sheldonSkaggs.setUnitStrength(4);
            sheldonSkaggs.setAbility(Ability.NONE);
            sheldonSkaggs.setType(Type.NONE);
            sheldonSkaggs.setPrice(10);
            sheldonSkaggs.setRange(Range.RANGED);
            sheldonSkaggs.setFraction(Fraction.NORTHERN_REALMS);
            sheldonSkaggs.setImage(CardsLoader.getImageBytes("Sheldon Skaggs.png"));

            Card dethmold = new Card(6);
            dethmold.setId(700L);
            dethmold.setName("Dethmold");
            dethmold.setUnitStrength(6);
            dethmold.setAbility(Ability.NONE);
            dethmold.setType(Type.NONE);
            dethmold.setPrice(20);
            dethmold.setRange(Range.RANGED);
            dethmold.setFraction(Fraction.NORTHERN_REALMS);
            dethmold.setImage(CardsLoader.getImageBytes("Dethmold.png"));

            Card princeStennis = new Card(5);
            princeStennis.setId(711L);
            princeStennis.setName("Prince Stennis");
            princeStennis.setUnitStrength(5);
            princeStennis.setAbility(Ability.SPY);
            princeStennis.setType(Type.NONE);
            princeStennis.setPrice(10);
            princeStennis.setRange(Range.CLOSE_COMBAT);
            princeStennis.setFraction(Fraction.NORTHERN_REALMS);
            princeStennis.setImage(CardsLoader.getImageBytes("Prince Stennis.png"));

            Card firstTrebuchet = new Card(6);
            firstTrebuchet.setId(722L);
            firstTrebuchet.setName("Trebuchet");
            firstTrebuchet.setUnitStrength(6);
            firstTrebuchet.setAbility(Ability.NONE);
            firstTrebuchet.setType(Type.NONE);
            firstTrebuchet.setPrice(20);
            firstTrebuchet.setRange(Range.SIEGE);
            firstTrebuchet.setFraction(Fraction.NORTHERN_REALMS);
            firstTrebuchet.setImage(CardsLoader.getImageBytes("Second Trebuchet.png"));

            Card secondTrebuchet = new Card(6);
            secondTrebuchet.setId(733L);
            secondTrebuchet.setName("Trebuchet");
            secondTrebuchet.setUnitStrength(6);
            secondTrebuchet.setAbility(Ability.NONE);
            secondTrebuchet.setType(Type.NONE);
            secondTrebuchet.setPrice(20);
            firstTrebuchet.setRange(Range.SIEGE);
            firstTrebuchet.setFraction(Fraction.NORTHERN_REALMS);
            firstTrebuchet.setImage(CardsLoader.getImageBytes("Trebuchet.png"));

            Card firstPoorFuckingInfantry = new Card(1);
            firstPoorFuckingInfantry.setId(744L);
            firstPoorFuckingInfantry.setName("Poor F*cking Infantry");
            firstPoorFuckingInfantry.setUnitStrength(1);
            firstPoorFuckingInfantry.setAbility(Ability.TIGHT_BOND);
            firstPoorFuckingInfantry.setType(Type.NONE);
            firstPoorFuckingInfantry.setPrice(20);
            firstPoorFuckingInfantry.setRange(Range.CLOSE_COMBAT);
            firstPoorFuckingInfantry.setFraction(Fraction.NORTHERN_REALMS);
            firstPoorFuckingInfantry.setImage(CardsLoader.getImageBytes("Poor Fcking Infantry.png"));

            Card secondPoorFuckingInfantry = new Card(1);
            secondPoorFuckingInfantry.setId(755L);
            secondPoorFuckingInfantry.setName("Poor F*cking Infantry");
            secondPoorFuckingInfantry.setUnitStrength(1);
            secondPoorFuckingInfantry.setAbility(Ability.TIGHT_BOND);
            secondPoorFuckingInfantry.setType(Type.NONE);
            secondPoorFuckingInfantry.setPrice(20);
            secondPoorFuckingInfantry.setRange(Range.CLOSE_COMBAT);
            secondPoorFuckingInfantry.setFraction(Fraction.NORTHERN_REALMS);
            secondPoorFuckingInfantry.setImage(CardsLoader.getImageBytes("Poor Fcking Infantry.png"));

            Card thirdPoorFuckingInfantry = new Card(1);
            thirdPoorFuckingInfantry.setId(766L);
            thirdPoorFuckingInfantry.setName("Poor F*cking Infantry");
            thirdPoorFuckingInfantry.setUnitStrength(1);
            thirdPoorFuckingInfantry.setAbility(Ability.TIGHT_BOND);
            thirdPoorFuckingInfantry.setType(Type.NONE);
            thirdPoorFuckingInfantry.setPrice(20);
            thirdPoorFuckingInfantry.setRange(Range.CLOSE_COMBAT);
            thirdPoorFuckingInfantry.setFraction(Fraction.NORTHERN_REALMS);
            thirdPoorFuckingInfantry.setImage(CardsLoader.getImageBytes("Poor Fcking Infantry.png"));

            Card firstCrinfridReaversDragonHunter = new Card(5);
            firstCrinfridReaversDragonHunter.setId(777L);
            firstCrinfridReaversDragonHunter.setName("Crinfrid Reavers Dragon Hunter");
            firstCrinfridReaversDragonHunter.setUnitStrength(5);
            firstCrinfridReaversDragonHunter.setAbility(Ability.TIGHT_BOND);
            firstCrinfridReaversDragonHunter.setType(Type.NONE);
            firstCrinfridReaversDragonHunter.setPrice(20);
            firstCrinfridReaversDragonHunter.setRange(Range.RANGED);
            firstCrinfridReaversDragonHunter.setFraction(Fraction.NORTHERN_REALMS);
            firstCrinfridReaversDragonHunter.setImage(CardsLoader.getImageBytes("Crinfrid Reavers Dragon Hunter.png"));

            Card secondCrinfridReaversDragonHunter = new Card(5);
            secondCrinfridReaversDragonHunter.setId(788L);
            secondCrinfridReaversDragonHunter.setName("Crinfrid Reavers Dragon Hunter");
            secondCrinfridReaversDragonHunter.setUnitStrength(5);
            secondCrinfridReaversDragonHunter.setAbility(Ability.TIGHT_BOND);
            secondCrinfridReaversDragonHunter.setType(Type.NONE);
            secondCrinfridReaversDragonHunter.setPrice(50);
            secondCrinfridReaversDragonHunter.setRange(Range.RANGED);
            secondCrinfridReaversDragonHunter.setFraction(Fraction.NORTHERN_REALMS);
            secondCrinfridReaversDragonHunter.setImage(CardsLoader.getImageBytes("Crinfrid Reavers Dragon Hunter.png"));

            Card thirdCrinfridReaversDragonHunter = new Card(5);
            thirdCrinfridReaversDragonHunter.setId(799L);
            thirdCrinfridReaversDragonHunter.setName("Crinfrid Reavers Dragon Hunter");
            thirdCrinfridReaversDragonHunter.setUnitStrength(5);
            thirdCrinfridReaversDragonHunter.setAbility(Ability.TIGHT_BOND);
            thirdCrinfridReaversDragonHunter.setType(Type.NONE);
            thirdCrinfridReaversDragonHunter.setPrice(50);
            thirdCrinfridReaversDragonHunter.setRange(Range.RANGED);
            thirdCrinfridReaversDragonHunter.setFraction(Fraction.NORTHERN_REALMS);
            thirdCrinfridReaversDragonHunter.setImage(CardsLoader.getImageBytes("Crinfrid Reavers Dragon Hunter.png"));

            Card firstRedanianFootSoldier = new Card(1);
            firstRedanianFootSoldier.setId(810L);
            firstRedanianFootSoldier.setName("Redanian Foot Soldier");
            firstRedanianFootSoldier.setUnitStrength(1);
            firstRedanianFootSoldier.setAbility(Ability.NONE);
            firstRedanianFootSoldier.setType(Type.NONE);
            firstRedanianFootSoldier.setPrice(10);
            firstRedanianFootSoldier.setRange(Range.CLOSE_COMBAT);
            firstRedanianFootSoldier.setFraction(Fraction.NORTHERN_REALMS);
            firstRedanianFootSoldier.setImage(CardsLoader.getImageBytes("Redanian Foot Soldier.png"));

            Card secondRedanianFootSoldier = new Card(1);
            secondRedanianFootSoldier.setId(821L);
            secondRedanianFootSoldier.setName("Redanian Foot Soldier");
            secondRedanianFootSoldier.setUnitStrength(1);
            secondRedanianFootSoldier.setAbility(Ability.NONE);
            secondRedanianFootSoldier.setType(Type.NONE);
            secondRedanianFootSoldier.setPrice(10);
            secondRedanianFootSoldier.setRange(Range.CLOSE_COMBAT);
            secondRedanianFootSoldier.setFraction(Fraction.NORTHERN_REALMS);
            secondRedanianFootSoldier.setImage(CardsLoader.getImageBytes("Second Redanian Foot Soldier.png"));

            Card firstCatapult = new Card(8);
            firstCatapult.setId(832L);
            firstCatapult.setName("Catapult");
            firstCatapult.setUnitStrength(8);
            firstCatapult.setAbility(Ability.TIGHT_BOND);
            firstCatapult.setType(Type.NONE);
            firstCatapult.setPrice(50);
            firstCatapult.setRange(Range.SIEGE);
            firstCatapult.setFraction(Fraction.NORTHERN_REALMS);
            firstCatapult.setImage(CardsLoader.getImageBytes("Catapult.png"));

            Card secondCatapult = new Card(8);
            secondCatapult.setId(843L);
            secondCatapult.setName("Catapult");
            secondCatapult.setUnitStrength(8);
            secondCatapult.setAbility(Ability.TIGHT_BOND);
            secondCatapult.setType(Type.NONE);
            secondCatapult.setPrice(50);
            secondCatapult.setRange(Range.SIEGE);
            secondCatapult.setFraction(Fraction.NORTHERN_REALMS);
            secondCatapult.setImage(CardsLoader.getImageBytes("Catapult.png"));

            Card firstBallista = new Card(6);
            firstBallista.setId(854L);
            firstBallista.setName("Ballista");
            firstBallista.setUnitStrength(6);
            firstBallista.setAbility(Ability.NONE);
            firstBallista.setType(Type.NONE);
            firstBallista.setPrice(20);
            firstBallista.setRange(Range.SIEGE);
            firstBallista.setFraction(Fraction.NORTHERN_REALMS);
            firstBallista.setImage(CardsLoader.getImageBytes("Ballista.png"));

            Card secondBallista = new Card(6);
            secondBallista.setId(865L);
            secondBallista.setName("Ballista");
            secondBallista.setUnitStrength(6);
            secondBallista.setAbility(Ability.NONE);
            secondBallista.setType(Type.NONE);
            secondBallista.setPrice(20);
            secondBallista.setRange(Range.SIEGE);
            secondBallista.setFraction(Fraction.NORTHERN_REALMS);
            secondBallista.setImage(CardsLoader.getImageBytes("Ballista.png"));

            Card firstKaedweniSiegeExpert = new Card(1);
            firstKaedweniSiegeExpert.setId(876L);
            firstKaedweniSiegeExpert.setName("Kaedweni Siege Expert");
            firstKaedweniSiegeExpert.setUnitStrength(1);
            firstKaedweniSiegeExpert.setAbility(Ability.MORALE_BOOST);
            firstKaedweniSiegeExpert.setType(Type.NONE);
            firstKaedweniSiegeExpert.setPrice(20);
            firstKaedweniSiegeExpert.setRange(Range.SIEGE);
            firstKaedweniSiegeExpert.setFraction(Fraction.NORTHERN_REALMS);
            firstKaedweniSiegeExpert.setImage(CardsLoader.getImageBytes("Kaedweni Siege Expert.png"));

            Card secondKaedweniSiegeExpert = new Card(1);
            secondKaedweniSiegeExpert.setId(887L);
            secondKaedweniSiegeExpert.setName("Kaedweni Siege Expert");
            secondKaedweniSiegeExpert.setUnitStrength(1);
            secondKaedweniSiegeExpert.setAbility(Ability.MORALE_BOOST);
            secondKaedweniSiegeExpert.setType(Type.NONE);
            secondKaedweniSiegeExpert.setPrice(20);
            secondKaedweniSiegeExpert.setRange(Range.SIEGE);
            secondKaedweniSiegeExpert.setFraction(Fraction.NORTHERN_REALMS);
            secondKaedweniSiegeExpert.setImage(CardsLoader.getImageBytes("Second Kaedweni Siege Expert.png"));

            Card thirdKaedweniSiegeExpert = new Card(1);
            thirdKaedweniSiegeExpert.setId(898L);
            thirdKaedweniSiegeExpert.setName("Kaedweni Siege Expert");
            thirdKaedweniSiegeExpert.setUnitStrength(1);
            thirdKaedweniSiegeExpert.setAbility(Ability.MORALE_BOOST);
            thirdKaedweniSiegeExpert.setType(Type.NONE);
            thirdKaedweniSiegeExpert.setPrice(50);
            secondKaedweniSiegeExpert.setRange(Range.SIEGE);
            secondKaedweniSiegeExpert.setFraction(Fraction.NORTHERN_REALMS);
            secondKaedweniSiegeExpert.setImage(CardsLoader.getImageBytes("Third Kaedweni Siege Expert.png"));

            Card firstBlueStripesCommando = new Card(4);
            firstBlueStripesCommando.setId(909L);
            firstBlueStripesCommando.setName("Blue Stripes Commando");
            firstBlueStripesCommando.setUnitStrength(4);
            firstBlueStripesCommando.setAbility(Ability.TIGHT_BOND);
            firstBlueStripesCommando.setType(Type.NONE);
            firstBlueStripesCommando.setPrice(20);
            firstBlueStripesCommando.setRange(Range.CLOSE_COMBAT);
            firstBlueStripesCommando.setFraction(Fraction.NORTHERN_REALMS);
            firstBlueStripesCommando.setImage(CardsLoader.getImageBytes("Blue Stripes Commando.png"));

            Card secondBlueStripesCommando = new Card(4);
            secondBlueStripesCommando.setId(920L);
            secondBlueStripesCommando.setName("Blue Stripes Commando");
            secondBlueStripesCommando.setUnitStrength(4);
            secondBlueStripesCommando.setAbility(Ability.TIGHT_BOND);
            secondBlueStripesCommando.setType(Type.NONE);
            secondBlueStripesCommando.setPrice(50);
            secondBlueStripesCommando.setRange(Range.CLOSE_COMBAT);
            secondBlueStripesCommando.setFraction(Fraction.NORTHERN_REALMS);
            secondBlueStripesCommando.setImage(CardsLoader.getImageBytes("Blue Stripes Commando.png"));

            Card thirdBlueStripesCommando = new Card(4);
            thirdBlueStripesCommando.setId(931L);
            thirdBlueStripesCommando.setName("Blue Stripes Commando");
            thirdBlueStripesCommando.setUnitStrength(4);
            thirdBlueStripesCommando.setAbility(Ability.TIGHT_BOND);
            thirdBlueStripesCommando.setType(Type.NONE);
            thirdBlueStripesCommando.setPrice(50);
            thirdBlueStripesCommando.setRange(Range.CLOSE_COMBAT);
            thirdBlueStripesCommando.setFraction(Fraction.NORTHERN_REALMS);
            thirdBlueStripesCommando.setImage(CardsLoader.getImageBytes("Blue Stripes Commando.png"));

            Card siegeTower = new Card(6);
            siegeTower.setId(942L);
            siegeTower.setName("Siege Tower");
            siegeTower.setUnitStrength(6);
            siegeTower.setAbility(Ability.NONE);
            siegeTower.setType(Type.NONE);
            siegeTower.setPrice(20);
            siegeTower.setRange(Range.SIEGE);
            siegeTower.setFraction(Fraction.NORTHERN_REALMS);
            siegeTower.setImage(CardsLoader.getImageBytes("Siege Tower.png"));

            Card dunBannerMedic = new Card(5);
            dunBannerMedic.setId(953L);
            dunBannerMedic.setName("Dun Banner Medic");
            dunBannerMedic.setUnitStrength(5);
            dunBannerMedic.setAbility(Ability.MEDIC);
            dunBannerMedic.setType(Type.NONE);
            dunBannerMedic.setPrice(20);
            dunBannerMedic.setRange(Range.SIEGE);
            dunBannerMedic.setFraction(Fraction.NORTHERN_REALMS);
            dunBannerMedic.setImage(CardsLoader.getImageBytes("Dun Banner Medic.png"));

            List<Card> entities = Arrays.asList(
                    vernonRoche, johnNatalis, esteradThyssen, philippaEilhart, thaler, ves,
                    siegfriedOfDenesle, yarpenZigrin, sigismundDijkstra, keiraMetz, sileDeTansarville, sabrinaGlevissig,
                    sheldonSkaggs, dethmold, princeStennis, firstTrebuchet, secondTrebuchet, firstPoorFuckingInfantry,
                    secondPoorFuckingInfantry, thirdPoorFuckingInfantry, firstCrinfridReaversDragonHunter, secondCrinfridReaversDragonHunter,
                    thirdCrinfridReaversDragonHunter, firstRedanianFootSoldier, secondRedanianFootSoldier, firstCatapult,
                    secondCatapult, firstBallista, secondBallista, firstKaedweniSiegeExpert, secondKaedweniSiegeExpert,
                    thirdKaedweniSiegeExpert, firstBlueStripesCommando, secondBlueStripesCommando, thirdBlueStripesCommando,
                    siegeTower, dunBannerMedic
            );

            entities.forEach(e -> {
                if (e.getId() == null || e.getId() == 0L) {
                    log.info("ERROR CARD: {}", e);
                    throw new RuntimeException(e.toString());
                }
            });
            cardRepo.saveAll(
                    entities
            );
        };
    }

    @Bean
    CommandLineRunner saveNGCards(CardRepo cardRepo) {
        return args -> {
            Card lethoOfGulet = new Card(10);
            lethoOfGulet.setId(979L);
            lethoOfGulet.setName("Letho of Gulet");
            lethoOfGulet.setUnitStrength(10);
            lethoOfGulet.setAbility(Ability.NONE);
            lethoOfGulet.setType(Type.HERO);
            lethoOfGulet.setPrice(50);
            lethoOfGulet.setRange(Range.CLOSE_COMBAT);
            lethoOfGulet.setFraction(Fraction.NILGAARD);
            lethoOfGulet.setImage(CardsLoader.getImageBytes("Letho of Gulet.png"));

            Card mennoCoehoorn = new Card(10);
            mennoCoehoorn.setId(990L);
            mennoCoehoorn.setName("Menno Coehoorn");
            mennoCoehoorn.setUnitStrength(10);
            mennoCoehoorn.setAbility(Ability.MEDIC);
            mennoCoehoorn.setType(Type.HERO);
            mennoCoehoorn.setPrice(50);
            mennoCoehoorn.setRange(Range.CLOSE_COMBAT);
            mennoCoehoorn.setFraction(Fraction.NILGAARD);
            mennoCoehoorn.setImage(CardsLoader.getImageBytes("Menno Coehoorn.png"));

            Card morvranVoorhis = new Card(10);
            morvranVoorhis.setId(1001L);
            morvranVoorhis.setName("Morvran Voorhis");
            morvranVoorhis.setUnitStrength(10);
            morvranVoorhis.setAbility(Ability.NONE);
            morvranVoorhis.setType(Type.HERO);
            morvranVoorhis.setPrice(50);
            morvranVoorhis.setRange(Range.SIEGE);
            morvranVoorhis.setFraction(Fraction.NILGAARD);
            morvranVoorhis.setImage(CardsLoader.getImageBytes("Morvran Voorhis.png"));

            Card tiborEggebracht = new Card(10);
            tiborEggebracht.setId(1012L);
            tiborEggebracht.setName("Tibor Eggebracht");
            tiborEggebracht.setUnitStrength(10);
            tiborEggebracht.setAbility(Ability.NONE);
            tiborEggebracht.setType(Type.HERO);
            tiborEggebracht.setPrice(50);
            tiborEggebracht.setRange(Range.RANGED);
            tiborEggebracht.setFraction(Fraction.NILGAARD);
            tiborEggebracht.setImage(CardsLoader.getImageBytes("Tibor Eggebracht.png"));

            Card albrich = new Card(2);
            albrich.setId(1023L);
            albrich.setName("Albrich");
            albrich.setUnitStrength(2);
            albrich.setAbility(Ability.NONE);
            albrich.setType(Type.NONE);
            albrich.setPrice(10);
            albrich.setRange(Range.RANGED);
            albrich.setFraction(Fraction.NILGAARD);
            albrich.setImage(CardsLoader.getImageBytes("Albrich.png"));

            Card assireVarAnahid = new Card(6);
            assireVarAnahid.setId(1034L);
            assireVarAnahid.setName("Assire var Anahid");
            assireVarAnahid.setUnitStrength(6);
            assireVarAnahid.setAbility(Ability.NONE);
            assireVarAnahid.setType(Type.NONE);
            assireVarAnahid.setPrice(20);
            assireVarAnahid.setRange(Range.RANGED);
            assireVarAnahid.setFraction(Fraction.NILGAARD);
            assireVarAnahid.setImage(CardsLoader.getImageBytes("Assire var Anahid.png"));

            Card cynthia = new Card(4);
            cynthia.setId(1045L);
            cynthia.setName("Cynthia");
            cynthia.setUnitStrength(4);
            cynthia.setAbility(Ability.NONE);
            cynthia.setType(Type.NONE);
            cynthia.setPrice(10);
            cynthia.setRange(Range.RANGED);
            cynthia.setFraction(Fraction.NILGAARD);
            cynthia.setImage(CardsLoader.getImageBytes("Cynthia.png"));

            Card fringillaVigo = new Card(6);
            fringillaVigo.setId(1056L);
            fringillaVigo.setName("Fringilla Vigo");
            fringillaVigo.setUnitStrength(6);
            fringillaVigo.setAbility(Ability.NONE);
            fringillaVigo.setType(Type.NONE);
            fringillaVigo.setPrice(20);
            fringillaVigo.setRange(Range.RANGED);
            fringillaVigo.setFraction(Fraction.NILGAARD);
            fringillaVigo.setImage(CardsLoader.getImageBytes("Fringilla Vigo.png"));

            Card morteisen = new Card(3);
            morteisen.setId(1067L);
            morteisen.setName("Morteisen");
            morteisen.setUnitStrength(3);
            morteisen.setAbility(Ability.NONE);
            morteisen.setType(Type.NONE);
            morteisen.setPrice(10);
            morteisen.setRange(Range.CLOSE_COMBAT);
            morteisen.setFraction(Fraction.NILGAARD);
            morteisen.setImage(CardsLoader.getImageBytes("Morteisen.png"));

            Card rainfarn = new Card(4);
            rainfarn.setId(1078L);
            rainfarn.setName("Rainfarn");
            rainfarn.setUnitStrength(4);
            rainfarn.setAbility(Ability.NONE);
            rainfarn.setType(Type.NONE);
            rainfarn.setPrice(10);
            rainfarn.setRange(Range.CLOSE_COMBAT);
            rainfarn.setFraction(Fraction.NILGAARD);
            rainfarn.setImage(CardsLoader.getImageBytes("Rainfarn.png"));

            Card renualdAepMatsen = new Card(5);
            renualdAepMatsen.setId(1089L);
            renualdAepMatsen.setName("Renuald aep Matsen");
            renualdAepMatsen.setUnitStrength(5);
            renualdAepMatsen.setAbility(Ability.NONE);
            renualdAepMatsen.setType(Type.NONE);
            renualdAepMatsen.setPrice(10);
            renualdAepMatsen.setRange(Range.RANGED);
            renualdAepMatsen.setFraction(Fraction.NILGAARD);
            renualdAepMatsen.setImage(CardsLoader.getImageBytes("Renuald aep Matsen.png"));

            Card rottenMangonel = new Card(3);
            rottenMangonel.setId(100L);
            rottenMangonel.setName("Rotten Mangonel");
            rottenMangonel.setUnitStrength(3);
            rottenMangonel.setAbility(Ability.NONE);
            rottenMangonel.setType(Type.NONE);
            rottenMangonel.setPrice(10);
            rottenMangonel.setRange(Range.SIEGE);
            rottenMangonel.setFraction(Fraction.NILGAARD);
            rottenMangonel.setImage(CardsLoader.getImageBytes("Rotten Mangonel.png"));

            Card shilardFitzOesterlen = new Card(7);
            shilardFitzOesterlen.setId(1111L);
            shilardFitzOesterlen.setName("Shilard Fitz-Oesterlen");
            shilardFitzOesterlen.setUnitStrength(7);
            shilardFitzOesterlen.setAbility(Ability.SPY);
            shilardFitzOesterlen.setType(Type.NONE);
            shilardFitzOesterlen.setPrice(20);
            shilardFitzOesterlen.setRange(Range.CLOSE_COMBAT);
            shilardFitzOesterlen.setFraction(Fraction.NILGAARD);
            shilardFitzOesterlen.setImage(CardsLoader.getImageBytes("Shilard Fitz-Oesterlen.png"));

            Card stefanSkellen = new Card(9);
            stefanSkellen.setId(1122L);
            stefanSkellen.setName("Stefan Skellen");
            stefanSkellen.setUnitStrength(9);
            stefanSkellen.setAbility(Ability.SPY);
            stefanSkellen.setType(Type.NONE);
            stefanSkellen.setPrice(50);
            stefanSkellen.setRange(Range.CLOSE_COMBAT);
            stefanSkellen.setFraction(Fraction.NILGAARD);
            stefanSkellen.setImage(CardsLoader.getImageBytes("Stefan Skellen.png"));

            Card sweers = new Card(2);
            sweers.setId(1133L);
            sweers.setName("Sweers");
            sweers.setUnitStrength(2);
            sweers.setAbility(Ability.NONE);
            sweers.setType(Type.NONE);
            sweers.setPrice(10);
            sweers.setRange(Range.RANGED);
            sweers.setFraction(Fraction.NILGAARD);
            sweers.setImage(CardsLoader.getImageBytes("Sweers.png"));

            Card vanhemar = new Card(4);
            vanhemar.setId(1144L);
            vanhemar.setName("Vanhemar");
            vanhemar.setUnitStrength(4);
            vanhemar.setAbility(Ability.NONE);
            vanhemar.setType(Type.NONE);
            vanhemar.setPrice(10);
            vanhemar.setRange(Range.RANGED);
            vanhemar.setFraction(Fraction.NILGAARD);
            vanhemar.setImage(CardsLoader.getImageBytes("Vanhemar.png"));

            Card vattierDeRideaux = new Card(4);
            vattierDeRideaux.setId(1155L);
            vattierDeRideaux.setName("Vattier de Rideaux");
            vattierDeRideaux.setUnitStrength(4);
            vattierDeRideaux.setAbility(Ability.SPY);
            vattierDeRideaux.setType(Type.NONE);
            vattierDeRideaux.setPrice(10);
            vattierDeRideaux.setRange(Range.CLOSE_COMBAT);
            vattierDeRideaux.setRange(Range.RANGED);
            vattierDeRideaux.setFraction(Fraction.NILGAARD);
            vattierDeRideaux.setImage(CardsLoader.getImageBytes("Vattier de Rideaux.png"));

            Card vreemde = new Card(2);
            vreemde.setId(1167L);
            vreemde.setName("Vreemde");
            vreemde.setUnitStrength(2);
            vreemde.setAbility(Ability.NONE);
            vreemde.setType(Type.NONE);
            vreemde.setPrice(10);
            vreemde.setRange(Range.CLOSE_COMBAT);
            vreemde.setFraction(Fraction.NILGAARD);
            vreemde.setImage(CardsLoader.getImageBytes("Vreemde.png"));

            Card cahirMawrDyffrynAepCeallach = new Card(6);
            cahirMawrDyffrynAepCeallach.setId(1178L);
            cahirMawrDyffrynAepCeallach.setName("Cahir Mawr Dyffryn aep Ceallach");
            cahirMawrDyffrynAepCeallach.setUnitStrength(6);
            cahirMawrDyffrynAepCeallach.setAbility(Ability.NONE);
            cahirMawrDyffrynAepCeallach.setType(Type.NONE);
            cahirMawrDyffrynAepCeallach.setPrice(20);
            cahirMawrDyffrynAepCeallach.setRange(Range.CLOSE_COMBAT);
            cahirMawrDyffrynAepCeallach.setFraction(Fraction.NILGAARD);
            cahirMawrDyffrynAepCeallach.setImage(CardsLoader.getImageBytes("Cahir Mawr Dyffryn aep Ceallach.png"));

            Card puttkammer = new Card(3);
            puttkammer.setId(1189L);
            puttkammer.setName("Puttkammer");
            puttkammer.setUnitStrength(3);
            puttkammer.setAbility(Ability.NONE);
            puttkammer.setType(Type.NONE);
            puttkammer.setPrice(10);
            puttkammer.setRange(Range.RANGED);
            puttkammer.setFraction(Fraction.NILGAARD);
            puttkammer.setImage(CardsLoader.getImageBytes("Puttkammer.png"));

            Card firstEtolianAuxiliaryArchers = new Card(1);
            firstEtolianAuxiliaryArchers.setId(1200L);
            firstEtolianAuxiliaryArchers.setName("Etolian Auxiliary Archers");
            firstEtolianAuxiliaryArchers.setUnitStrength(1);
            firstEtolianAuxiliaryArchers.setAbility(Ability.MEDIC);
            firstEtolianAuxiliaryArchers.setType(Type.NONE);
            firstEtolianAuxiliaryArchers.setPrice(50);
            firstEtolianAuxiliaryArchers.setRange(Range.RANGED);
            firstEtolianAuxiliaryArchers.setFraction(Fraction.NILGAARD);
            firstEtolianAuxiliaryArchers.setImage(CardsLoader.getImageBytes("First Etolian Auxiliary Archers.png"));

            Card secondEtolianAuxiliaryArchers = new Card(1);
            secondEtolianAuxiliaryArchers.setId(1211L);
            secondEtolianAuxiliaryArchers.setName("Etolian Auxiliary Archers");
            secondEtolianAuxiliaryArchers.setUnitStrength(1);
            secondEtolianAuxiliaryArchers.setAbility(Ability.MEDIC);
            secondEtolianAuxiliaryArchers.setType(Type.NONE);
            secondEtolianAuxiliaryArchers.setPrice(50);
            secondEtolianAuxiliaryArchers.setRange(Range.RANGED);
            secondEtolianAuxiliaryArchers.setFraction(Fraction.NILGAARD);
            secondEtolianAuxiliaryArchers.setImage(CardsLoader.getImageBytes("Second Etolian Auxiliary Archers.png"));

            Card firstBlackInfantryArcher = new Card(10);
            firstBlackInfantryArcher.setId(1222L);
            firstBlackInfantryArcher.setName("Black Infantry Archer");
            firstBlackInfantryArcher.setUnitStrength(10);
            firstBlackInfantryArcher.setAbility(Ability.NONE);
            firstBlackInfantryArcher.setType(Type.NONE);
            firstBlackInfantryArcher.setPrice(50);
            firstBlackInfantryArcher.setRange(Range.RANGED);
            firstBlackInfantryArcher.setFraction(Fraction.NILGAARD);
            firstBlackInfantryArcher.setImage(CardsLoader.getImageBytes("Black Infantry Archer.png"));

            Card siegeTechnican = new Card(0);
            siegeTechnican.setId(1233L);
            siegeTechnican.setName("Siege Technican");
            siegeTechnican.setUnitStrength(0);
            siegeTechnican.setAbility(Ability.MEDIC);
            siegeTechnican.setType(Type.NONE);
            siegeTechnican.setPrice(20);
            siegeTechnican.setRange(Range.SIEGE);
            siegeTechnican.setFraction(Fraction.NILGAARD);
            siegeTechnican.setImage(CardsLoader.getImageBytes("Siege Technican.png"));

            Card secondBlackInfantryArcher = new Card(10);
            secondBlackInfantryArcher.setId(1244L);
            secondBlackInfantryArcher.setName("Black Infantry Archer");
            secondBlackInfantryArcher.setUnitStrength(10);
            secondBlackInfantryArcher.setAbility(Ability.NONE);
            secondBlackInfantryArcher.setType(Type.NONE);
            secondBlackInfantryArcher.setPrice(50);
            secondBlackInfantryArcher.setRange(Range.RANGED);
            secondBlackInfantryArcher.setFraction(Fraction.NILGAARD);
            secondBlackInfantryArcher.setImage(CardsLoader.getImageBytes("Black Infantry Archer Alternative.png"));

            Card heavyZerrikanianFireScorpionn = new Card(10);
            heavyZerrikanianFireScorpionn.setId(1255L);
            heavyZerrikanianFireScorpionn.setName("Heavy Zerrikanian Fire Scorpionn");
            heavyZerrikanianFireScorpionn.setUnitStrength(10);
            heavyZerrikanianFireScorpionn.setAbility(Ability.NONE);
            heavyZerrikanianFireScorpionn.setType(Type.NONE);
            heavyZerrikanianFireScorpionn.setPrice(50);
            heavyZerrikanianFireScorpionn.setRange(Range.SIEGE);
            heavyZerrikanianFireScorpionn.setFraction(Fraction.NILGAARD);
            heavyZerrikanianFireScorpionn.setImage(CardsLoader.getImageBytes("Heavy Zerrikanian Fire Scorpion.png"));

            Card zerrikanianFireScorpionn = new Card(10);
            zerrikanianFireScorpionn.setId(1266L);
            zerrikanianFireScorpionn.setName("Zerrikanian Fire Scorpionn");
            zerrikanianFireScorpionn.setUnitStrength(10);
            zerrikanianFireScorpionn.setAbility(Ability.NONE);
            zerrikanianFireScorpionn.setType(Type.NONE);
            zerrikanianFireScorpionn.setPrice(50);
            zerrikanianFireScorpionn.setRange(Range.SIEGE);
            zerrikanianFireScorpionn.setFraction(Fraction.NILGAARD);
            zerrikanianFireScorpionn.setImage(CardsLoader.getImageBytes("Heavy Zerrikanian Fire Scorpion.png"));

            Card firstImperaBrigade = new Card(3);
            firstImperaBrigade.setId(1277L);
            firstImperaBrigade.setName("Impera Brigade");
            firstImperaBrigade.setUnitStrength(3);
            firstImperaBrigade.setAbility(Ability.TIGHT_BOND);
            firstImperaBrigade.setType(Type.NONE);
            firstImperaBrigade.setPrice(20);
            firstImperaBrigade.setRange(Range.CLOSE_COMBAT);
            firstImperaBrigade.setFraction(Fraction.NILGAARD);
            firstImperaBrigade.setImage(CardsLoader.getImageBytes("Impera Brigade.png"));

            Card secondImperaBrigade = new Card(3);
            secondImperaBrigade.setId(1288L);
            secondImperaBrigade.setName("Impera Brigade");
            secondImperaBrigade.setUnitStrength(3);
            secondImperaBrigade.setAbility(Ability.TIGHT_BOND);
            secondImperaBrigade.setType(Type.NONE);
            secondImperaBrigade.setPrice(20);
            secondImperaBrigade.setRange(Range.CLOSE_COMBAT);
            secondImperaBrigade.setFraction(Fraction.NILGAARD);
            secondImperaBrigade.setImage(CardsLoader.getImageBytes("Impera Brigade.png"));

            Card thirdImperaBrigade = new Card(3);
            thirdImperaBrigade.setId(1299L);
            thirdImperaBrigade.setName("Impera Brigade");
            thirdImperaBrigade.setUnitStrength(3);
            thirdImperaBrigade.setAbility(Ability.TIGHT_BOND);
            thirdImperaBrigade.setType(Type.NONE);
            thirdImperaBrigade.setPrice(20);
            thirdImperaBrigade.setRange(Range.CLOSE_COMBAT);
            thirdImperaBrigade.setFraction(Fraction.NILGAARD);
            thirdImperaBrigade.setImage(CardsLoader.getImageBytes("Impera Brigade.png"));

            Card fourthImperaBrigade = new Card(3);
            fourthImperaBrigade.setId(1310L);
            fourthImperaBrigade.setName("Impera Brigade");
            fourthImperaBrigade.setUnitStrength(3);
            fourthImperaBrigade.setAbility(Ability.TIGHT_BOND);
            fourthImperaBrigade.setType(Type.NONE);
            fourthImperaBrigade.setPrice(20);
            fourthImperaBrigade.setRange(Range.CLOSE_COMBAT);
            fourthImperaBrigade.setFraction(Fraction.NILGAARD);
            fourthImperaBrigade.setImage(CardsLoader.getImageBytes("Impera Brigade.png"));

            Card firstNausicaaCavalryBrigade = new Card(2);
            firstNausicaaCavalryBrigade.setId(1321L);
            firstNausicaaCavalryBrigade.setName("Nausicaa Cavalry Brigade");
            firstNausicaaCavalryBrigade.setUnitStrength(2);
            firstNausicaaCavalryBrigade.setAbility(Ability.TIGHT_BOND);
            firstNausicaaCavalryBrigade.setType(Type.NONE);
            firstNausicaaCavalryBrigade.setPrice(20);
            firstNausicaaCavalryBrigade.setRange(Range.CLOSE_COMBAT);
            firstNausicaaCavalryBrigade.setFraction(Fraction.NILGAARD);
            firstNausicaaCavalryBrigade.setImage(CardsLoader.getImageBytes("Nausicaa Cavalry Brigade.png"));

            Card secondNausicaaCavalryBrigade = new Card(2);
            secondNausicaaCavalryBrigade.setId(1332L);
            secondNausicaaCavalryBrigade.setName("Nausicaa Cavalry Brigade");
            secondNausicaaCavalryBrigade.setUnitStrength(2);
            secondNausicaaCavalryBrigade.setAbility(Ability.TIGHT_BOND);
            secondNausicaaCavalryBrigade.setType(Type.NONE);
            secondNausicaaCavalryBrigade.setPrice(20);
            secondNausicaaCavalryBrigade.setRange(Range.CLOSE_COMBAT);
            secondNausicaaCavalryBrigade.setFraction(Fraction.NILGAARD);
            secondNausicaaCavalryBrigade.setImage(CardsLoader.getImageBytes("Nausicaa Cavalry Brigade.png"));

            Card thirdNausicaaCavalryBrigade = new Card(2);
            thirdNausicaaCavalryBrigade.setId(1343L);
            thirdNausicaaCavalryBrigade.setName("Nausicaa Cavalry Brigade");
            thirdNausicaaCavalryBrigade.setUnitStrength(2);
            thirdNausicaaCavalryBrigade.setAbility(Ability.TIGHT_BOND);
            thirdNausicaaCavalryBrigade.setType(Type.NONE);
            thirdNausicaaCavalryBrigade.setPrice(20);
            thirdNausicaaCavalryBrigade.setRange(Range.CLOSE_COMBAT);
            thirdNausicaaCavalryBrigade.setFraction(Fraction.NILGAARD);
            thirdNausicaaCavalryBrigade.setImage(CardsLoader.getImageBytes("Nausicaa Cavalry Brigade.png"));

            Card siegeEngineer = new Card(6);
            siegeEngineer.setId(1354L);
            siegeEngineer.setName("Siege Engineer");
            siegeEngineer.setUnitStrength(6);
            siegeEngineer.setAbility(Ability.NONE);
            siegeEngineer.setType(Type.NONE);
            siegeEngineer.setPrice(20);
            siegeEngineer.setRange(Range.SIEGE);
            siegeEngineer.setFraction(Fraction.NILGAARD);
            siegeEngineer.setImage(CardsLoader.getImageBytes("Siege Engineer.png"));

            Card firstYoungEmissary = new Card(5);
            firstYoungEmissary.setId(1365L);
            firstYoungEmissary.setName("Young Emissary");
            firstYoungEmissary.setUnitStrength(5);
            firstYoungEmissary.setAbility(Ability.TIGHT_BOND);
            firstYoungEmissary.setType(Type.NONE);
            firstYoungEmissary.setPrice(20);
            firstYoungEmissary.setRange(Range.CLOSE_COMBAT);
            firstYoungEmissary.setFraction(Fraction.NILGAARD);
            firstYoungEmissary.setImage(CardsLoader.getImageBytes("Young Emissary.png"));

            Card secondYoungEmissary = new Card(5);
            secondYoungEmissary.setId(1376L);
            secondYoungEmissary.setName("Young Emissary");
            secondYoungEmissary.setUnitStrength(5);
            secondYoungEmissary.setAbility(Ability.TIGHT_BOND);
            secondYoungEmissary.setType(Type.NONE);
            secondYoungEmissary.setPrice(50);
            secondYoungEmissary.setRange(Range.CLOSE_COMBAT);
            secondYoungEmissary.setFraction(Fraction.NILGAARD);
            secondYoungEmissary.setImage(CardsLoader.getImageBytes("Second Young Emissary.png"));

            List<Card> entities = Arrays.asList(lethoOfGulet, mennoCoehoorn, morvranVoorhis, tiborEggebracht, albrich, assireVarAnahid,
                    cynthia, fringillaVigo, morteisen, rainfarn, renualdAepMatsen, rottenMangonel, shilardFitzOesterlen,
                    stefanSkellen, sweers, vanhemar, vattierDeRideaux, vreemde, cahirMawrDyffrynAepCeallach, puttkammer,
                    firstEtolianAuxiliaryArchers, secondEtolianAuxiliaryArchers, firstBlackInfantryArcher, secondBlackInfantryArcher,
                    siegeTechnican, heavyZerrikanianFireScorpionn, zerrikanianFireScorpionn, firstImperaBrigade, secondImperaBrigade,
                    thirdImperaBrigade, fourthImperaBrigade, firstNausicaaCavalryBrigade, secondNausicaaCavalryBrigade,
                    thirdNausicaaCavalryBrigade, siegeEngineer, firstYoungEmissary, secondYoungEmissary);

            entities.forEach(e -> {
                if (e.getId() == null || e.getId() == 0L) {
                    log.info("ERROR CARD: {}", e);
                    throw new RuntimeException(e.toString());
                }
            });

            cardRepo.saveAll(entities);
        };
    }

    @Bean
    CommandLineRunner saveSTCards(CardRepo cardRepo) {
        return args -> {
            Card eithne = new Card(10);
            eithne.setId(1401L);
            eithne.setName("Eithné");
            eithne.setUnitStrength(10);
            eithne.setAbility(Ability.NONE);
            eithne.setType(Type.HERO);
            eithne.setPrice(50);
            eithne.setRange(Range.RANGED);
            eithne.setFraction(Fraction.SCOIATAEL);
            eithne.setImage(CardsLoader.getImageBytes("Eithné.png"));

            Card saskia = new Card(10);
            saskia.setId(1412L);
            saskia.setName("Saskia & Saesenthessis");
            saskia.setUnitStrength(10);
            saskia.setAbility(Ability.NONE);
            saskia.setType(Type.HERO);
            saskia.setPrice(50);
            saskia.setRange(Range.RANGED);
            saskia.setFraction(Fraction.SCOIATAEL);
            saskia.setImage(CardsLoader.getImageBytes("Saskia & Saesenthessis.png"));

            Card isengrimFaoiltiarna = new Card(10);
            isengrimFaoiltiarna.setId(1423L);
            isengrimFaoiltiarna.setName("Isengrim Faoiltiarna");
            isengrimFaoiltiarna.setUnitStrength(10);
            isengrimFaoiltiarna.setAbility(Ability.MORALE_BOOST);
            isengrimFaoiltiarna.setType(Type.NONE);
            isengrimFaoiltiarna.setPrice(50);
            isengrimFaoiltiarna.setRange(Range.CLOSE_COMBAT);
            isengrimFaoiltiarna.setFraction(Fraction.SCOIATAEL);
            isengrimFaoiltiarna.setImage(CardsLoader.getImageBytes("Isengrim Faoiltiarna.png"));

            Card iorveth = new Card(10);
            iorveth.setId(1434L);
            iorveth.setName("Iorveth");
            iorveth.setUnitStrength(10);
            iorveth.setAbility(Ability.NONE);
            iorveth.setType(Type.HERO);
            iorveth.setPrice(50);
            iorveth.setRange(Range.RANGED);
            iorveth.setFraction(Fraction.SCOIATAEL);
            iorveth.setImage(CardsLoader.getImageBytes("Iorveth.png"));

            Card dennisCranmer = new Card(6);
            dennisCranmer.setId(1445L);
            dennisCranmer.setName("Dennis Cranmer");
            dennisCranmer.setUnitStrength(6);
            dennisCranmer.setAbility(Ability.NONE);
            dennisCranmer.setType(Type.NONE);
            dennisCranmer.setPrice(20);
            dennisCranmer.setRange(Range.CLOSE_COMBAT);
            dennisCranmer.setFraction(Fraction.SCOIATAEL);
            dennisCranmer.setImage(CardsLoader.getImageBytes("Dennis Cranmer.png"));

            Card milva = new Card(10);
            milva.setId(1456L);
            milva.setName("Milva");
            milva.setUnitStrength(10);
            milva.setAbility(Ability.MORALE_BOOST);
            milva.setType(Type.NONE);
            milva.setPrice(50);
            milva.setRange(Range.RANGED);
            milva.setFraction(Fraction.SCOIATAEL);
            milva.setImage(CardsLoader.getImageBytes("Milva.png"));

            Card idaEmean = new Card(6);
            idaEmean.setId(1467L);
            idaEmean.setName("Ida Emean");
            idaEmean.setUnitStrength(6);
            idaEmean.setAbility(Ability.NONE);
            idaEmean.setType(Type.NONE);
            idaEmean.setPrice(20);
            idaEmean.setRange(Range.RANGED);
            idaEmean.setFraction(Fraction.SCOIATAEL);
            idaEmean.setImage(CardsLoader.getImageBytes("Ida Emean.png"));

            Card filavandrel = new Card(6);
            filavandrel.setId(1478L);
            filavandrel.setName("Filavandrel");
            filavandrel.setUnitStrength(6);
            filavandrel.setAbility(Ability.NONE);
            filavandrel.setType(Type.NONE);
            filavandrel.setPrice(20);
            filavandrel.setRange(Range.AGILE);
            filavandrel.setFraction(Fraction.SCOIATAEL);
            filavandrel.setImage(CardsLoader.getImageBytes("Filavandrel.png"));

            Card yaevinn = new Card(6);
            yaevinn.setId(1489L);
            yaevinn.setName("Yaevinn");
            yaevinn.setUnitStrength(6);
            yaevinn.setAbility(Ability.NONE);
            yaevinn.setType(Type.NONE);
            yaevinn.setPrice(50);
            yaevinn.setRange(Range.AGILE);
            yaevinn.setFraction(Fraction.SCOIATAEL);
            yaevinn.setImage(CardsLoader.getImageBytes("Yaevinn.png"));

            Card toruviel = new Card(2);
            toruviel.setId(1500L);
            toruviel.setName("Toruviel");
            toruviel.setUnitStrength(2);
            toruviel.setAbility(Ability.NONE);
            toruviel.setType(Type.NONE);
            toruviel.setPrice(10);
            toruviel.setRange(Range.RANGED);
            toruviel.setFraction(Fraction.SCOIATAEL);
            toruviel.setImage(CardsLoader.getImageBytes("Toruviel.png"));

            Card riordain = new Card(1);
            riordain.setId(1511L);
            riordain.setName("Riordain");
            riordain.setUnitStrength(1);
            riordain.setAbility(Ability.NONE);
            riordain.setType(Type.NONE);
            riordain.setPrice(10);
            riordain.setRange(Range.RANGED);
            riordain.setFraction(Fraction.SCOIATAEL);
            riordain.setImage(CardsLoader.getImageBytes("Riordain.png"));

            Card ciaranAepEasnillien = new Card(3);
            ciaranAepEasnillien.setId(1522L);
            ciaranAepEasnillien.setName("Ciaran aep Easnillien");
            ciaranAepEasnillien.setUnitStrength(3);
            ciaranAepEasnillien.setAbility(Ability.NONE);
            ciaranAepEasnillien.setType(Type.NONE);
            ciaranAepEasnillien.setPrice(10);
            ciaranAepEasnillien.setRange(Range.AGILE);
            ciaranAepEasnillien.setFraction(Fraction.SCOIATAEL);
            ciaranAepEasnillien.setImage(CardsLoader.getImageBytes("Ciaran aep Easnillien.png"));

            Card barclayEls = new Card(6);
            barclayEls.setId(1533L);
            barclayEls.setName("Barclay Els");
            barclayEls.setUnitStrength(6);
            barclayEls.setAbility(Ability.NONE);
            barclayEls.setType(Type.NONE);
            barclayEls.setPrice(20);
            barclayEls.setRange(Range.AGILE);
            barclayEls.setFraction(Fraction.SCOIATAEL);
            barclayEls.setImage(CardsLoader.getImageBytes("Barclay Els.png"));

            Card firstHavekarHealer = new Card(0);
            firstHavekarHealer.setId(1544L);
            firstHavekarHealer.setName("Havekar Healer");
            firstHavekarHealer.setUnitStrength(0);
            firstHavekarHealer.setAbility(Ability.MEDIC);
            firstHavekarHealer.setType(Type.NONE);
            firstHavekarHealer.setPrice(10);
            firstHavekarHealer.setRange(Range.RANGED);
            firstHavekarHealer.setFraction(Fraction.SCOIATAEL);
            firstHavekarHealer.setImage(CardsLoader.getImageBytes("Havekar Healer.png"));

            Card secondHavekarHealer = new Card(0);
            secondHavekarHealer.setId(1555L);
            secondHavekarHealer.setName("Havekar Healer");
            secondHavekarHealer.setUnitStrength(0);
            secondHavekarHealer.setAbility(Ability.MEDIC);
            secondHavekarHealer.setType(Type.NONE);
            secondHavekarHealer.setPrice(20);
            secondHavekarHealer.setRange(Range.RANGED);
            secondHavekarHealer.setFraction(Fraction.SCOIATAEL);
            secondHavekarHealer.setImage(CardsLoader.getImageBytes("Second Havekar Healer.png"));

            Card thirdHavekarHealer = new Card(0);
            thirdHavekarHealer.setId(1566L);
            thirdHavekarHealer.setName("Havekar Healer");
            thirdHavekarHealer.setUnitStrength(0);
            thirdHavekarHealer.setAbility(Ability.MEDIC);
            thirdHavekarHealer.setType(Type.NONE);
            thirdHavekarHealer.setPrice(50);
            thirdHavekarHealer.setRange(Range.RANGED);
            thirdHavekarHealer.setFraction(Fraction.SCOIATAEL);
            thirdHavekarHealer.setImage(CardsLoader.getImageBytes("Third Havekar Healer.png"));

            Card firstVriheddBrigadeVeteran = new Card(5);
            firstVriheddBrigadeVeteran.setId(1577L);
            firstVriheddBrigadeVeteran.setName("Vrihedd Brigade Veteran");
            firstVriheddBrigadeVeteran.setUnitStrength(5);
            firstVriheddBrigadeVeteran.setAbility(Ability.NONE);
            firstVriheddBrigadeVeteran.setType(Type.NONE);
            firstVriheddBrigadeVeteran.setPrice(20);
            firstVriheddBrigadeVeteran.setRange(Range.AGILE);
            firstVriheddBrigadeVeteran.setFraction(Fraction.SCOIATAEL);
            firstVriheddBrigadeVeteran.setImage(CardsLoader.getImageBytes("Vrihedd Brigade Veteran.png"));

            Card secondVriheddBrigadeVeteran = new Card(5);
            secondVriheddBrigadeVeteran.setId(1588L);
            secondVriheddBrigadeVeteran.setName("Vrihedd Brigade Veteran");
            secondVriheddBrigadeVeteran.setUnitStrength(5);
            secondVriheddBrigadeVeteran.setAbility(Ability.NONE);
            secondVriheddBrigadeVeteran.setType(Type.NONE);
            secondVriheddBrigadeVeteran.setPrice(20);
            secondVriheddBrigadeVeteran.setRange(Range.AGILE);
            secondVriheddBrigadeVeteran.setFraction(Fraction.SCOIATAEL);
            secondVriheddBrigadeVeteran.setImage(CardsLoader.getImageBytes("Second Vrihedd Brigade Veteran.png"));

            Card firstDolBlathannaArcher = new Card(6);
            firstDolBlathannaArcher.setId(1599L);
            firstDolBlathannaArcher.setName("Dol Blathanna Archer");
            firstDolBlathannaArcher.setUnitStrength(6);
            firstDolBlathannaArcher.setAbility(Ability.NONE);
            firstDolBlathannaArcher.setType(Type.NONE);
            firstDolBlathannaArcher.setPrice(10);
            firstDolBlathannaArcher.setRange(Range.RANGED);
            firstDolBlathannaArcher.setFraction(Fraction.SCOIATAEL);
            firstDolBlathannaArcher.setImage(CardsLoader.getImageBytes("Dol Blathanna Archer.png"));

            Card secondDolBlathannaArcher = new Card(6);
            secondDolBlathannaArcher.setId(1610L);
            secondDolBlathannaArcher.setName("Dol Blathanna Archer");
            secondDolBlathannaArcher.setUnitStrength(6);
            secondDolBlathannaArcher.setAbility(Ability.NONE);
            secondDolBlathannaArcher.setType(Type.NONE);
            secondDolBlathannaArcher.setPrice(10);
            secondDolBlathannaArcher.setRange(Range.RANGED);
            secondDolBlathannaArcher.setFraction(Fraction.SCOIATAEL);
            secondDolBlathannaArcher.setImage(CardsLoader.getImageBytes("Dol Blathanna Archer.png"));

            Card firstDolBlathannaScout = new Card(6);
            firstDolBlathannaScout.setId(1621L);
            firstDolBlathannaScout.setName("Dol Blathanna Scout");
            firstDolBlathannaScout.setUnitStrength(6);
            firstDolBlathannaScout.setAbility(Ability.NONE);
            firstDolBlathannaScout.setType(Type.NONE);
            firstDolBlathannaScout.setPrice(10);
            firstDolBlathannaScout.setRange(Range.AGILE);
            firstDolBlathannaScout.setFraction(Fraction.SCOIATAEL);
            firstDolBlathannaScout.setImage(CardsLoader.getImageBytes("Dol Blathanna Scout.png"));

            Card secondDolBlathannaScout = new Card(6);
            secondDolBlathannaScout.setId(1632L);
            secondDolBlathannaScout.setName("Dol Blathanna Scout");
            secondDolBlathannaScout.setUnitStrength(6);
            secondDolBlathannaScout.setAbility(Ability.NONE);
            secondDolBlathannaScout.setType(Type.NONE);
            secondDolBlathannaScout.setPrice(10);
            firstDolBlathannaScout.setRange(Range.AGILE);
            firstDolBlathannaScout.setFraction(Fraction.SCOIATAEL);
            firstDolBlathannaScout.setImage(CardsLoader.getImageBytes("Second Dol Blathanna Scout.png"));

            Card firstDwarfSkirmisher = new Card(3);
            firstDwarfSkirmisher.setId(1643L);
            firstDwarfSkirmisher.setName("Dwarf Skirmisher");
            firstDwarfSkirmisher.setUnitStrength(3);
            firstDwarfSkirmisher.setAbility(Ability.MUSTER);
            firstDwarfSkirmisher.setType(Type.NONE);
            firstDwarfSkirmisher.setPrice(10);
            firstDwarfSkirmisher.setRange(Range.CLOSE_COMBAT);
            firstDwarfSkirmisher.setFraction(Fraction.SCOIATAEL);
            firstDwarfSkirmisher.setImage(CardsLoader.getImageBytes("Dwarf Skirmisher.png"));

            Card secondDwarfSkirmisher = new Card(3);
            secondDwarfSkirmisher.setId(1654L);
            secondDwarfSkirmisher.setName("Dwarf Skirmisher");
            secondDwarfSkirmisher.setUnitStrength(3);
            secondDwarfSkirmisher.setAbility(Ability.MUSTER);
            secondDwarfSkirmisher.setType(Type.NONE);
            secondDwarfSkirmisher.setPrice(10);
            secondDwarfSkirmisher.setRange(Range.CLOSE_COMBAT);
            secondDwarfSkirmisher.setFraction(Fraction.SCOIATAEL);
            secondDwarfSkirmisher.setImage(CardsLoader.getImageBytes("Second Dwarf Skirmisher.png"));

            Card thirdDwarfSkirmisher = new Card(3);
            thirdDwarfSkirmisher.setId(1665L);
            thirdDwarfSkirmisher.setName("Dwarf Skirmisher");
            thirdDwarfSkirmisher.setUnitStrength(3);
            thirdDwarfSkirmisher.setAbility(Ability.MUSTER);
            thirdDwarfSkirmisher.setType(Type.NONE);
            thirdDwarfSkirmisher.setPrice(10);
            thirdDwarfSkirmisher.setRange(Range.CLOSE_COMBAT);
            thirdDwarfSkirmisher.setFraction(Fraction.SCOIATAEL);
            thirdDwarfSkirmisher.setImage(CardsLoader.getImageBytes("Third Dwarf Skirmisher.png"));

            Card firstMahakamanDefender = new Card(5);
            firstMahakamanDefender.setId(1676L);
            firstMahakamanDefender.setName("Mahakaman Defender");
            firstMahakamanDefender.setUnitStrength(5);
            firstMahakamanDefender.setAbility(Ability.NONE);
            firstMahakamanDefender.setType(Type.NONE);
            firstMahakamanDefender.setPrice(20);
            firstMahakamanDefender.setRange(Range.CLOSE_COMBAT);
            firstMahakamanDefender.setFraction(Fraction.SCOIATAEL);
            firstMahakamanDefender.setImage(CardsLoader.getImageBytes("Mahakaman Defender.png"));

            Card secondMahakamanDefender = new Card(5);
            secondMahakamanDefender.setId(1687L);
            secondMahakamanDefender.setName("Mahakaman Defender");
            secondMahakamanDefender.setUnitStrength(5);
            secondMahakamanDefender.setAbility(Ability.NONE);
            secondMahakamanDefender.setType(Type.NONE);
            secondMahakamanDefender.setPrice(20);
            secondMahakamanDefender.setRange(Range.CLOSE_COMBAT);
            secondMahakamanDefender.setFraction(Fraction.SCOIATAEL);
            secondMahakamanDefender.setImage(CardsLoader.getImageBytes("Second Mahakaman Defender.png"));

            Card thirdMahakamanDefender = new Card(5);
            thirdMahakamanDefender.setId(1698L);
            thirdMahakamanDefender.setName("Mahakaman Defender");
            thirdMahakamanDefender.setUnitStrength(5);
            thirdMahakamanDefender.setAbility(Ability.NONE);
            thirdMahakamanDefender.setType(Type.NONE);
            thirdMahakamanDefender.setPrice(20);
            thirdMahakamanDefender.setRange(Range.CLOSE_COMBAT);
            thirdMahakamanDefender.setFraction(Fraction.SCOIATAEL);
            thirdMahakamanDefender.setImage(CardsLoader.getImageBytes("Third Mahakaman Defender.png"));

            Card fourthMahakamanDefender = new Card(5);
            fourthMahakamanDefender.setId(1709L);
            fourthMahakamanDefender.setName("Mahakaman Defender");
            fourthMahakamanDefender.setUnitStrength(5);
            fourthMahakamanDefender.setAbility(Ability.NONE);
            fourthMahakamanDefender.setType(Type.NONE);
            fourthMahakamanDefender.setPrice(20);
            fourthMahakamanDefender.setRange(Range.CLOSE_COMBAT);
            fourthMahakamanDefender.setFraction(Fraction.SCOIATAEL);
            fourthMahakamanDefender.setImage(CardsLoader.getImageBytes("Fourth Mahakaman Defender.png"));

            Card fifthMahakamanDefender = new Card(5);
            fifthMahakamanDefender.setId(1720L);
            fifthMahakamanDefender.setName("Mahakaman Defender");
            fifthMahakamanDefender.setUnitStrength(5);
            fifthMahakamanDefender.setAbility(Ability.NONE);
            fifthMahakamanDefender.setType(Type.NONE);
            fifthMahakamanDefender.setPrice(20);
            fifthMahakamanDefender.setRange(Range.CLOSE_COMBAT);
            fifthMahakamanDefender.setFraction(Fraction.SCOIATAEL);
            fifthMahakamanDefender.setImage(CardsLoader.getImageBytes("Fifth Mahakaman Defender.png"));

            Card firstElvenSkirmisher = new Card(2);
            firstElvenSkirmisher.setId(1791L);
            firstElvenSkirmisher.setName("Elven Skirmisher");
            firstElvenSkirmisher.setUnitStrength(2);
            firstElvenSkirmisher.setAbility(Ability.MUSTER);
            firstElvenSkirmisher.setType(Type.NONE);
            firstElvenSkirmisher.setPrice(10);
            firstElvenSkirmisher.setRange(Range.RANGED);
            firstElvenSkirmisher.setFraction(Fraction.SCOIATAEL);
            firstElvenSkirmisher.setImage(CardsLoader.getImageBytes("Elven Skirmisher.png"));

            Card secondElvenSkirmisher = new Card(2);
            secondElvenSkirmisher.setId(1742L);
            secondElvenSkirmisher.setName("Elven Skirmisher");
            secondElvenSkirmisher.setUnitStrength(2);
            secondElvenSkirmisher.setAbility(Ability.MUSTER);
            secondElvenSkirmisher.setType(Type.NONE);
            secondElvenSkirmisher.setPrice(10);
            secondElvenSkirmisher.setRange(Range.RANGED);
            secondElvenSkirmisher.setFraction(Fraction.SCOIATAEL);
            secondElvenSkirmisher.setImage(CardsLoader.getImageBytes("Second Elven Skirmisher.png"));

            Card thirdElvenSkirmisher = new Card(2);
            thirdElvenSkirmisher.setId(1753L);
            thirdElvenSkirmisher.setName("Elven Skirmisher");
            thirdElvenSkirmisher.setUnitStrength(2);
            thirdElvenSkirmisher.setAbility(Ability.MUSTER);
            thirdElvenSkirmisher.setType(Type.NONE);
            thirdElvenSkirmisher.setPrice(10);
            thirdElvenSkirmisher.setRange(Range.RANGED);
            thirdElvenSkirmisher.setFraction(Fraction.SCOIATAEL);
            thirdElvenSkirmisher.setImage(CardsLoader.getImageBytes("Third Elven Skirmisher.png"));

            Card vriheddCadet = new Card(4);
            vriheddCadet.setId(1764L);
            vriheddCadet.setName("Vrihedd Cadet");
            vriheddCadet.setUnitStrength(4);
            vriheddCadet.setAbility(Ability.NONE);
            vriheddCadet.setType(Type.NONE);
            vriheddCadet.setPrice(10);
            vriheddCadet.setRange(Range.AGILE);
            vriheddCadet.setFraction(Fraction.SCOIATAEL);
            vriheddCadet.setImage(CardsLoader.getImageBytes("Vrihedd Brigade Veteran.png"));

            Card firstHavekarSmuggler = new Card(5);
            firstHavekarSmuggler.setId(1775L);
            firstHavekarSmuggler.setName("Havekar Smuggler");
            firstHavekarSmuggler.setUnitStrength(5);
            firstHavekarSmuggler.setAbility(Ability.MUSTER);
            firstHavekarSmuggler.setType(Type.NONE);
            firstHavekarSmuggler.setPrice(10);
            firstHavekarSmuggler.setRange(Range.CLOSE_COMBAT);
            firstHavekarSmuggler.setFraction(Fraction.SCOIATAEL);
            firstHavekarSmuggler.setImage(CardsLoader.getImageBytes("Havekar Smuggler.png"));

            Card secondHavekarSmuggler = new Card(5);
            secondHavekarSmuggler.setId(1786L);
            secondHavekarSmuggler.setName("Havekar Smuggler");
            secondHavekarSmuggler.setUnitStrength(5);
            secondHavekarSmuggler.setAbility(Ability.MUSTER);
            secondHavekarSmuggler.setType(Type.NONE);
            secondHavekarSmuggler.setPrice(10);
            secondHavekarSmuggler.setRange(Range.CLOSE_COMBAT);
            secondHavekarSmuggler.setFraction(Fraction.SCOIATAEL);
            secondHavekarSmuggler.setImage(CardsLoader.getImageBytes("Second Havekar Smuggler.png"));

            Card thirdHavekarSmuggler = new Card(5);
            thirdHavekarSmuggler.setId(1797L);
            thirdHavekarSmuggler.setName("Havekar Smuggler");
            thirdHavekarSmuggler.setUnitStrength(5);
            thirdHavekarSmuggler.setAbility(Ability.MUSTER);
            thirdHavekarSmuggler.setType(Type.NONE);
            thirdHavekarSmuggler.setPrice(20);
            thirdHavekarSmuggler.setRange(Range.CLOSE_COMBAT);
            thirdHavekarSmuggler.setFraction(Fraction.SCOIATAEL);
            thirdHavekarSmuggler.setImage(CardsLoader.getImageBytes("Third Havekar Smuggler.png"));

            List<Card> entities = Arrays.asList(eithne, saskia, isengrimFaoiltiarna, iorveth, dennisCranmer, milva, idaEmean,
                    filavandrel, yaevinn, toruviel, riordain, ciaranAepEasnillien, barclayEls, firstHavekarHealer,
                    secondHavekarHealer, thirdHavekarHealer, firstVriheddBrigadeVeteran, secondVriheddBrigadeVeteran,
                    firstDolBlathannaArcher, secondDolBlathannaArcher, firstDolBlathannaScout, secondDolBlathannaScout, firstDwarfSkirmisher,
                    secondDwarfSkirmisher, thirdDwarfSkirmisher, fifthMahakamanDefender, secondMahakamanDefender,
                    thirdMahakamanDefender, fourthMahakamanDefender, fifthMahakamanDefender, firstElvenSkirmisher,
                    secondElvenSkirmisher, vriheddCadet, firstHavekarSmuggler, secondHavekarSmuggler, thirdHavekarSmuggler);

            entities.forEach(e -> {
                if (e.getId() == null || e.getId() == 0L) {
                    log.info("ERROR CARD: {}", e);
                    throw new RuntimeException(e.toString());
                }
            });

            cardRepo.saveAll(entities);
        };
    }

    @Bean
    CommandLineRunner saveMOCards(CardRepo cardRepo) {
        return args -> {
            Card draug = new Card(10);
            draug.setId(1822L);
            draug.setName("Draug");
            draug.setUnitStrength(10);
            draug.setAbility(Ability.NONE);
            draug.setType(Type.HERO);
            draug.setPrice(50);
            draug.setRange(Range.CLOSE_COMBAT);
            draug.setFraction(Fraction.MONSTERS);
            draug.setImage(CardsLoader.getImageBytes("Draug.png"));

            Card kayran = new Card(18);
            kayran.setId(1833L);
            kayran.setName("Kayran");
            kayran.setUnitStrength(18);
            kayran.setAbility(Ability.MORALE_BOOST);
            kayran.setType(Type.HERO);
            kayran.setPrice(50);
            kayran.setRange(Range.AGILE);
            kayran.setFraction(Fraction.MONSTERS);
            kayran.setImage(CardsLoader.getImageBytes("Kayran.png"));

            Card imlerith = new Card(10);
            imlerith.setId(1844L);
            imlerith.setName("Imlerith");
            imlerith.setUnitStrength(10);
            imlerith.setAbility(Ability.NONE);
            imlerith.setType(Type.HERO);
            imlerith.setPrice(50);
            imlerith.setRange(Range.CLOSE_COMBAT);
            imlerith.setFraction(Fraction.MONSTERS);
            imlerith.setImage(CardsLoader.getImageBytes("Imlerith.png"));

            Card leshen = new Card(10);
            leshen.setId(1855L);
            leshen.setName("Leshen");
            leshen.setUnitStrength(10);
            leshen.setAbility(Ability.NONE);
            leshen.setType(Type.HERO);
            leshen.setPrice(50);
            leshen.setRange(Range.RANGED);
            leshen.setFraction(Fraction.MONSTERS);
            leshen.setImage(CardsLoader.getImageBytes("Leshen.png"));

            Card forktail = new Card(5);
            forktail.setId(1866L);
            forktail.setName("Forktail");
            forktail.setUnitStrength(5);
            forktail.setAbility(Ability.NONE);
            forktail.setType(Type.NONE);
            forktail.setPrice(10);
            forktail.setRange(Range.CLOSE_COMBAT);
            forktail.setFraction(Fraction.MONSTERS);
            forktail.setImage(CardsLoader.getImageBytes("Forktail.png"));

            Card earthElemental = new Card(6);
            earthElemental.setId(1877L);
            earthElemental.setName("Earth Elemental");
            earthElemental.setUnitStrength(6);
            earthElemental.setAbility(Ability.NONE);
            earthElemental.setType(Type.NONE);
            earthElemental.setPrice(20);
            earthElemental.setRange(Range.SIEGE);
            earthElemental.setFraction(Fraction.MONSTERS);
            earthElemental.setImage(CardsLoader.getImageBytes("Earth Elemental.png"));

            Card fiend = new Card(6);
            fiend.setId(1888L);
            fiend.setName("Fiend");
            fiend.setUnitStrength(6);
            fiend.setAbility(Ability.NONE);
            fiend.setType(Type.NONE);
            fiend.setPrice(20);
            fiend.setRange(Range.CLOSE_COMBAT);
            fiend.setFraction(Fraction.MONSTERS);
            fiend.setImage(CardsLoader.getImageBytes("Fiend.png"));

            Card plagueMaiden = new Card(5);
            plagueMaiden.setId(1899L);
            plagueMaiden.setName("Plague Maiden");
            plagueMaiden.setUnitStrength(5);
            plagueMaiden.setAbility(Ability.NONE);
            plagueMaiden.setType(Type.NONE);
            plagueMaiden.setPrice(20);
            plagueMaiden.setRange(Range.CLOSE_COMBAT);
            plagueMaiden.setFraction(Fraction.MONSTERS);
            plagueMaiden.setImage(CardsLoader.getImageBytes("Plague Maiden.png"));

            Card griffin = new Card(5);
            griffin.setId(1910L);
            griffin.setName("Griffin");
            griffin.setUnitStrength(5);
            griffin.setAbility(Ability.NONE);
            griffin.setType(Type.NONE);
            griffin.setPrice(10);
            griffin.setRange(Range.CLOSE_COMBAT);
            griffin.setFraction(Fraction.MONSTERS);
            griffin.setImage(CardsLoader.getImageBytes("Griffin.png"));

            Card werewolf = new Card(5);
            werewolf.setId(1921L);
            werewolf.setName("Werewolf");
            werewolf.setUnitStrength(5);
            werewolf.setAbility(Ability.NONE);
            werewolf.setType(Type.NONE);
            werewolf.setPrice(20);
            werewolf.setRange(Range.CLOSE_COMBAT);
            werewolf.setFraction(Fraction.MONSTERS);
            werewolf.setImage(CardsLoader.getImageBytes("Werewolf.png"));

            Card botchling = new Card(4);
            botchling.setId(1932L);
            botchling.setName("Botchling");
            botchling.setUnitStrength(4);
            botchling.setAbility(Ability.NONE);
            botchling.setType(Type.NONE);
            botchling.setPrice(10);
            botchling.setRange(Range.CLOSE_COMBAT);
            botchling.setFraction(Fraction.MONSTERS);
            botchling.setImage(CardsLoader.getImageBytes("Botchling.png"));

            Card frightener = new Card(5);
            frightener.setId(1943L);
            frightener.setName("Frightener");
            frightener.setUnitStrength(5);
            frightener.setAbility(Ability.NONE);
            frightener.setType(Type.NONE);
            frightener.setPrice(10);
            frightener.setRange(Range.CLOSE_COMBAT);
            frightener.setFraction(Fraction.MONSTERS);
            frightener.setImage(CardsLoader.getImageBytes("Frightener.png"));

            Card iceGiant = new Card(5);
            iceGiant.setId(1954L);
            iceGiant.setName("Ice Giant");
            iceGiant.setUnitStrength(5);
            iceGiant.setAbility(Ability.NONE);
            iceGiant.setType(Type.NONE);
            iceGiant.setPrice(10);
            iceGiant.setRange(Range.SIEGE);
            iceGiant.setFraction(Fraction.MONSTERS);
            iceGiant.setImage(CardsLoader.getImageBytes("Ice Giant.png"));

            Card endrega = new Card(2);
            endrega.setId(1965L);
            endrega.setName("Endrega");
            endrega.setUnitStrength(2);
            endrega.setAbility(Ability.NONE);
            endrega.setType(Type.NONE);
            endrega.setPrice(10);
            endrega.setRange(Range.RANGED);
            endrega.setFraction(Fraction.MONSTERS);
            endrega.setImage(CardsLoader.getImageBytes("Endrega.png"));

            Card harpy = new Card(2);
            harpy.setId(1976L);
            harpy.setName("Harpy");
            harpy.setUnitStrength(2);
            harpy.setAbility(Ability.NONE);
            harpy.setType(Type.NONE);
            harpy.setPrice(10);
            harpy.setRange(Range.AGILE);
            harpy.setFraction(Fraction.MONSTERS);
            harpy.setImage(CardsLoader.getImageBytes("Harpy.png"));

            Card cockatrice = new Card(2);
            cockatrice.setId(1987L);
            cockatrice.setName("Cockatrice");
            cockatrice.setUnitStrength(2);
            cockatrice.setAbility(Ability.NONE);
            cockatrice.setType(Type.NONE);
            cockatrice.setPrice(10);
            cockatrice.setRange(Range.RANGED);
            cockatrice.setFraction(Fraction.MONSTERS);
            cockatrice.setImage(CardsLoader.getImageBytes("Cockatrice.png"));

            Card gargoyle = new Card(2);
            gargoyle.setId(1998L);
            gargoyle.setName("Gargoyle");
            gargoyle.setUnitStrength(2);
            gargoyle.setAbility(Ability.NONE);
            gargoyle.setType(Type.NONE);
            gargoyle.setPrice(10);
            gargoyle.setRange(Range.RANGED);
            gargoyle.setFraction(Fraction.MONSTERS);
            gargoyle.setImage(CardsLoader.getImageBytes("Gargoyle.png"));

            Card celaenoHarpy = new Card(2);
            celaenoHarpy.setId(2009L);
            celaenoHarpy.setName("Celaeno Harpy");
            celaenoHarpy.setUnitStrength(2);
            celaenoHarpy.setAbility(Ability.NONE);
            celaenoHarpy.setType(Type.NONE);
            celaenoHarpy.setPrice(10);
            celaenoHarpy.setRange(Range.AGILE);
            celaenoHarpy.setFraction(Fraction.MONSTERS);
            celaenoHarpy.setImage(CardsLoader.getImageBytes("Celaeno Harpy.png"));

            Card graveHag = new Card(5);
            graveHag.setId(2020L);
            graveHag.setName("Grave Hag");
            graveHag.setUnitStrength(5);
            graveHag.setAbility(Ability.NONE);
            graveHag.setType(Type.NONE);
            graveHag.setPrice(10);
            graveHag.setRange(Range.RANGED);
            graveHag.setFraction(Fraction.MONSTERS);
            graveHag.setImage(CardsLoader.getImageBytes("Grave Hag.png"));

            Card fireElemental = new Card(6);
            fireElemental.setId(2031L);
            fireElemental.setName("Fire Elemental");
            fireElemental.setUnitStrength(6);
            fireElemental.setAbility(Ability.NONE);
            fireElemental.setType(Type.NONE);
            fireElemental.setPrice(20);
            fireElemental.setRange(Range.SIEGE);
            fireElemental.setFraction(Fraction.MONSTERS);
            fireElemental.setImage(CardsLoader.getImageBytes("Fire Elemental.png"));

            Card foglet = new Card(2);
            foglet.setId(2042L);
            foglet.setName("Foglet");
            foglet.setUnitStrength(2);
            foglet.setAbility(Ability.NONE);
            foglet.setType(Type.NONE);
            foglet.setPrice(10);
            foglet.setRange(Range.CLOSE_COMBAT);
            foglet.setFraction(Fraction.MONSTERS);
            foglet.setImage(CardsLoader.getImageBytes("Foglet.png"));

            Card wyvern = new Card(2);
            wyvern.setId(2053L);
            wyvern.setName("Wyvern");
            wyvern.setUnitStrength(2);
            wyvern.setAbility(Ability.NONE);
            wyvern.setType(Type.NONE);
            wyvern.setPrice(10);
            wyvern.setRange(Range.RANGED);
            wyvern.setFraction(Fraction.MONSTERS);
            wyvern.setImage(CardsLoader.getImageBytes("Wyvern.png"));

            Card arachasBehemoth = new Card(6);
            arachasBehemoth.setId(2064L);
            arachasBehemoth.setName("Arachas Behemoth");
            arachasBehemoth.setUnitStrength(6);
            arachasBehemoth.setAbility(Ability.MUSTER);
            arachasBehemoth.setType(Type.NONE);
            arachasBehemoth.setPrice(50);
            arachasBehemoth.setRange(Range.SIEGE);
            arachasBehemoth.setFraction(Fraction.MONSTERS);
            arachasBehemoth.setImage(CardsLoader.getImageBytes("Arachas Behemoth.png"));

            Card firstArachas = new Card(4);
            firstArachas.setId(2075L);
            firstArachas.setName("Arachas");
            firstArachas.setUnitStrength(4);
            firstArachas.setAbility(Ability.MUSTER);
            firstArachas.setType(Type.NONE);
            firstArachas.setPrice(20);
            firstArachas.setRange(Range.CLOSE_COMBAT);
            firstArachas.setFraction(Fraction.MONSTERS);
            firstArachas.setImage(CardsLoader.getImageBytes("Arachas.png"));

            Card secondArachas = new Card(4);
            secondArachas.setId(2086L);
            secondArachas.setName("Arachas");
            secondArachas.setUnitStrength(4);
            secondArachas.setAbility(Ability.MUSTER);
            secondArachas.setType(Type.NONE);
            secondArachas.setPrice(20);
            secondArachas.setRange(Range.SIEGE);
            secondArachas.setFraction(Fraction.MONSTERS);
            secondArachas.setImage(CardsLoader.getImageBytes("Arachas Behemoth.png"));

            Card thirdArachas = new Card(4);
            thirdArachas.setId(2096L);
            thirdArachas.setName("Arachas");
            thirdArachas.setUnitStrength(4);
            thirdArachas.setAbility(Ability.MUSTER);
            thirdArachas.setType(Type.NONE);
            thirdArachas.setPrice(20);
            thirdArachas.setRange(Range.CLOSE_COMBAT);
            thirdArachas.setFraction(Fraction.MONSTERS);
            thirdArachas.setImage(CardsLoader.getImageBytes("Second Arachas.png"));

            Card firstNekker = new Card(2);
            firstNekker.setId(2108L);
            firstNekker.setName("Nekker");
            firstNekker.setUnitStrength(2);
            firstNekker.setAbility(Ability.MUSTER);
            firstNekker.setType(Type.NONE);
            firstNekker.setPrice(20);
            firstNekker.setRange(Range.CLOSE_COMBAT);
            firstNekker.setFraction(Fraction.MONSTERS);
            firstNekker.setImage(CardsLoader.getImageBytes("Nekker.png"));

            Card secondNekker = new Card(2);
            secondNekker.setId(2119L);
            secondNekker.setName("Nekker");
            secondNekker.setUnitStrength(2);
            secondNekker.setAbility(Ability.MUSTER);
            secondNekker.setType(Type.NONE);
            secondNekker.setPrice(10);
            secondNekker.setRange(Range.CLOSE_COMBAT);
            secondNekker.setFraction(Fraction.MONSTERS);
            secondNekker.setImage(CardsLoader.getImageBytes("Second Nekker.png"));

            Card thirdNekker = new Card(2);
            thirdNekker.setId(2130L);
            thirdNekker.setName("Nekker");
            thirdNekker.setUnitStrength(2);
            thirdNekker.setAbility(Ability.MUSTER);
            thirdNekker.setType(Type.NONE);
            thirdNekker.setPrice(10);
            thirdNekker.setRange(Range.CLOSE_COMBAT);
            thirdNekker.setFraction(Fraction.MONSTERS);
            thirdNekker.setImage(CardsLoader.getImageBytes("Third Nekker.png"));

            Card vampireEkimmara = new Card(4);
            vampireEkimmara.setId(2141L);
            vampireEkimmara.setName("Vampire: Ekimmara");
            vampireEkimmara.setUnitStrength(4);
            vampireEkimmara.setAbility(Ability.MUSTER);
            vampireEkimmara.setType(Type.NONE);
            vampireEkimmara.setPrice(20);
            vampireEkimmara.setRange(Range.CLOSE_COMBAT);
            vampireEkimmara.setFraction(Fraction.MONSTERS);
            vampireEkimmara.setImage(CardsLoader.getImageBytes("Vampire Ekimmara.png"));

            Card vampireFleder = new Card(4);
            vampireFleder.setId(2152L);
            vampireFleder.setName("Vampire: Fleder");
            vampireFleder.setUnitStrength(4);
            vampireFleder.setAbility(Ability.MUSTER);
            vampireFleder.setType(Type.NONE);
            vampireFleder.setPrice(50);
            vampireFleder.setRange(Range.CLOSE_COMBAT);
            vampireFleder.setFraction(Fraction.MONSTERS);
            vampireFleder.setImage(CardsLoader.getImageBytes("Vampire Fleder.png"));

            Card vampireGarkain = new Card(4);
            vampireGarkain.setId(2163L);
            vampireGarkain.setName("Vampire: Garkain");
            vampireGarkain.setUnitStrength(4);
            vampireGarkain.setAbility(Ability.MUSTER);
            vampireGarkain.setType(Type.NONE);
            vampireGarkain.setPrice(50);
            vampireGarkain.setRange(Range.CLOSE_COMBAT);
            vampireGarkain.setFraction(Fraction.MONSTERS);
            vampireGarkain.setImage(CardsLoader.getImageBytes("Vampire Garkain.png"));

            Card vampireBruxa = new Card(4);
            vampireBruxa.setId(2174L);
            vampireBruxa.setName("Vampire: Bruxa");
            vampireBruxa.setUnitStrength(4);
            vampireBruxa.setAbility(Ability.MUSTER);
            vampireBruxa.setType(Type.NONE);
            vampireBruxa.setPrice(20);
            vampireBruxa.setRange(Range.CLOSE_COMBAT);
            vampireBruxa.setFraction(Fraction.MONSTERS);
            vampireBruxa.setImage(CardsLoader.getImageBytes("Vampire Bruxa.png"));

            Card vampireKatakan = new Card(5);
            vampireKatakan.setId(2185L);
            vampireKatakan.setName("Vampire: Katakan");
            vampireKatakan.setUnitStrength(5);
            vampireKatakan.setAbility(Ability.MUSTER);
            vampireKatakan.setType(Type.NONE);
            vampireKatakan.setPrice(50);
            vampireKatakan.setRange(Range.CLOSE_COMBAT);
            vampireKatakan.setFraction(Fraction.MONSTERS);
            vampireKatakan.setImage(CardsLoader.getImageBytes("Vampire Katakan.png"));

            Card firstGhoul = new Card(1);
            firstGhoul.setId(2196L);
            firstGhoul.setName("Ghoul");
            firstGhoul.setUnitStrength(1);
            firstGhoul.setAbility(Ability.MUSTER);
            firstGhoul.setType(Type.NONE);
            firstGhoul.setPrice(10);
            firstGhoul.setRange(Range.CLOSE_COMBAT);
            firstGhoul.setFraction(Fraction.MONSTERS);
            firstGhoul.setImage(CardsLoader.getImageBytes("Ghoul.png"));

            Card secondGhoul = new Card(1);
            secondGhoul.setId(2207L);
            secondGhoul.setName("Ghoul");
            secondGhoul.setUnitStrength(1);
            secondGhoul.setAbility(Ability.MUSTER);
            secondGhoul.setType(Type.NONE);
            secondGhoul.setPrice(10);
            secondGhoul.setRange(Range.CLOSE_COMBAT);
            secondGhoul.setFraction(Fraction.MONSTERS);
            secondGhoul.setImage(CardsLoader.getImageBytes("Second Ghoul.png"));

            Card thirdGhoul = new Card(1);
            thirdGhoul.setId(2218L);
            thirdGhoul.setName("Ghoul");
            thirdGhoul.setUnitStrength(1);
            thirdGhoul.setAbility(Ability.MUSTER);
            thirdGhoul.setType(Type.NONE);
            thirdGhoul.setPrice(10);
            thirdGhoul.setRange(Range.CLOSE_COMBAT);
            thirdGhoul.setFraction(Fraction.MONSTERS);
            thirdGhoul.setImage(CardsLoader.getImageBytes("Third Ghoul.png"));

            Card croneBrewess = new Card(6);
            croneBrewess.setId(2243L);
            croneBrewess.setName("Crone: Brewess");
            croneBrewess.setUnitStrength(6);
            croneBrewess.setAbility(Ability.MUSTER);
            croneBrewess.setType(Type.NONE);
            croneBrewess.setPrice(50);
            croneBrewess.setRange(Range.CLOSE_COMBAT);
            croneBrewess.setFraction(Fraction.MONSTERS);
            croneBrewess.setImage(CardsLoader.getImageBytes("Crone Brewess.png"));

            Card croneWeavess = new Card(6);
            croneWeavess.setId(2254L);
            croneWeavess.setName("Crone: Weavess");
            croneWeavess.setUnitStrength(6);
            croneWeavess.setAbility(Ability.MUSTER);
            croneWeavess.setType(Type.NONE);
            croneWeavess.setPrice(50);
            croneWeavess.setRange(Range.CLOSE_COMBAT);
            croneWeavess.setFraction(Fraction.MONSTERS);
            croneWeavess.setImage(CardsLoader.getImageBytes("Crone Weavess.png"));

            Card croneWhispess = new Card(6);
            croneWhispess.setId(2265L);
            croneWhispess.setName("Crone: Whispess");
            croneWhispess.setUnitStrength(6);
            croneWhispess.setAbility(Ability.MUSTER);
            croneWhispess.setType(Type.NONE);
            croneWhispess.setPrice(20);
            croneWhispess.setRange(Range.CLOSE_COMBAT);
            croneWhispess.setFraction(Fraction.MONSTERS);
            croneWhispess.setImage(CardsLoader.getImageBytes("Crone Whispess.png"));


            List<Card> entities = Arrays.asList(draug, kayran, imlerith, leshen, forktail, earthElemental, fiend, plagueMaiden,
                    griffin, werewolf, botchling, frightener, iceGiant, endrega, harpy, cockatrice, gargoyle, celaenoHarpy,
                    graveHag, fireElemental, foglet, wyvern, arachasBehemoth, firstArachas, secondArachas, thirdArachas,
                    firstNekker, secondNekker, thirdNekker, vampireEkimmara, vampireFleder, vampireGarkain, vampireBruxa,
                    vampireKatakan, firstGhoul, secondGhoul, thirdGhoul, croneBrewess, croneWeavess, croneWhispess);

            entities.forEach(e -> {
                if (e.getId() == null || e.getId() == 0L) {
                    log.info("ERROR CARD: {}", e);
                    throw new RuntimeException(e.toString());
                }
            });

            cardRepo.saveAll(entities);
        };
    }

    @Bean CommandLineRunner addSiegemasterCard(CardRepo repo) {
        return args -> {
            final Card entity = new Card();
            entity.setId(2348L);
            entity.setAbility(Ability.SPY);
            entity.setName("The Siegemaster");
            entity.setImage(CardsLoader.getImageBytes("The Siegemaster.png"));
            repo.save(entity);
        };
    }

    @Bean CommandLineRunner addPassCard(CardRepo repo) {
        return args -> {
            final Card entity = new Card();
            entity.setId(2349L);
            entity.setAbility(Ability.NONE);
            entity.setName("Pass");
            entity.setRange(Range.CLOSE_COMBAT);
            entity.setImage(CardsLoader.getImageBytes("Pass.png"));
            repo.save(entity);
        };
    }
    @Bean CommandLineRunner addMannequinCard(CardRepo repo) {
        return args -> {
            final Card entity = new Card();
            entity.setId(2371l);
            entity.setAbility(Ability.NONE);
            entity.setName("Decoy.png");
            entity.setImage(CardsLoader.getImageBytes("Decoy.png"));
            repo.save(entity);
        };
    }
}
