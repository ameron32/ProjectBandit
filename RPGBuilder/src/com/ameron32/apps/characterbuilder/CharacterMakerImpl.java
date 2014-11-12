package com.ameron32.apps.characterbuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.ameron32.rpgbuilder.Assets;
import com.ameron32.rpgbuilder.advantageaddon.Advantage;
import com.ameron32.rpgbuilder.advantageaddon.AdvTools.AdvType;
import com.ameron32.rpgbuilder.app.tools.Tools;
import com.ameron32.rpgbuilder.npcrecord.components.Gender;
import com.ameron32.rpgbuilder.npcrecord.components.Jobs;
import com.ameron32.rpgbuilder.npcrecord.components.MoreFeatures;
import com.ameron32.rpgbuilder.npcrecord.components.Names;
import com.ameron32.rpgbuilder.npcrecord.components.MoreFeatures.FeatureType;

public class CharacterMakerImpl
    implements CharacterMaker {
  
  private int ST, DX, IQ, HT;
  private int HP, Will, Per, FP, Dodge, BM, BL;
  private double BS;
  private int cost;
  private String name, job;
  private Gender gender;
  
  private List<Advantage> advantageRecord;
  private List<String> features;
  
  @Override public CharacterMaker fromBlank() {
    // TODO Auto-generated method stub
    return null;
  }
  
  @Override public CharacterMaker fromCharacter(
      Character character) {
    // TODO Auto-generated method stub
    return null;
  }
  
  @Override public CharacterMaker generate(
      List<Attribute> attrs) {
    for (int i = 0; i < attrs.size(); i++) {
      Attribute attribute = attrs.get(i);
      generate(attribute);
    }
    return null;
  }
  
  @Override public CharacterMaker generate(
      Attribute attr) {
    switch (attr) {
    case BL:
      break;
    case BM:
      break;
    case BS:
      break;
    case DX:
      DX = normalizedRandom();
      break;
    case Dodge:
      break;
    case FP:
      break;
    case HP:
      break;
    case HT:
      HT = normalizedRandom();
      break;
    case IQ:
      IQ = normalizedRandom();
      break;
    case Per:
      break;
    case ST:
      ST = normalizedRandom();
      break;
    case Will:
      break;
    case advantage1:
      break;
    case advantageAll:
      break;
    case advantageSome:
      break;
    case cost:
      break;
    case feature1:
      break;
    case featureAll:
      break;
    case featureSome:
      break;
    case gender:
      gender = generateGender();
      break;
    case job:
      break;
    case name:
      break;
    default:
      break;
    }
    return null;
  }
  
  @Override public Character create() {
    // TODO Auto-generated method stub
    return null;
  }
  
//random number with greater probability close to 10
 private static int normalizedRandom() {
   Random r = new Random();
   // chance for average 8-12 50%
   // chance for above-below average 6-14 45%
   // chance for ridiculous 3-17 5%
   int chance = r.nextInt();
   if (chance > 0.5) {
     // average
     return r.nextInt(6) + 1 + 7;
   } else if (chance > 0.05) {
     // above-below average
     return r.nextInt(10) + 1 + 5;
   } else {
     // ridiculous
     return r.nextInt(16) + 1 + 2;
   }
 }

 // random number with greater probability close to 10
 private static int normalizedRandomOf(int max) {
   Random r = new Random();
   // chance for average 8-12 50%
   // chance for above-below average 6-14 45%
   // chance for ridiculous 3-17 5%
   int chance = r.nextInt();
   int returnMe;
   double returnMeD;
   if (chance > 0.5) {
     // average
     returnMeD = r.nextInt(6) + 1 + 7;
   } else if (chance > 0.05) {
     // above-below average
     returnMeD = r.nextInt(10) + 1 + 5;
   } else {
     // ridiculous
     returnMeD = r.nextInt(16) + 1 + 2;
   }
   double maxD = max;
   returnMeD = returnMeD / 20.0 * maxD;
   returnMe = (int) Math.round(returnMeD);
   return returnMe;
 }

 public Gender generateGender() {
   Gender g = null;
   if (new Random().nextBoolean()) {
     g = Gender.male;
   } else {
     g = Gender.female;
   }
   return g;
 }
 

 public String generateName() {
   Names n = new Names();
   return n.getGeneratedName();
 }

 public String generateJob(Gender g) {
   Jobs j = new Jobs();
   return j.getGeneratedJobFor(g);
 }

 public List<Advantage> generateAdvantages() {
   return generateAdvantages(-1, -1, -1, -1);
 }
 
 public List<Advantage> generateAdvantages(int noOfAdvs, int noOfDisadvs,
     int noOfPerks, int noOfQuirks) {
   List<Advantage> generatedList = new ArrayList<Advantage>();
 
   int noOfAdvsi;
   int noOfDisadvsi;
   int noOfPerksi;
   int noOfQuirksi;
   if (noOfAdvs == -1) {
     noOfAdvsi = Tools.normalizedRandomOf(2) + 1;
   } else {
     noOfAdvsi = noOfAdvs;
   }
   if (noOfAdvs == -1) {
     noOfDisadvsi = Tools.normalizedRandomOf(2) + 1;
   } else {
     noOfDisadvsi = noOfDisadvs;
   }
   if (noOfAdvs == -1) {
     noOfPerksi = Tools.normalizedRandomOf(2) + 1;
   } else {
     noOfPerksi = noOfPerks;
   }
   if (noOfAdvs == -1) {
     noOfQuirksi = Tools.normalizedRandomOf(2) + 1;
   } else {
     noOfQuirksi = noOfQuirks;
   }
 
   generatedList.addAll(addAdvantages(noOfAdvsi, AdvType.advantage, generatedList));
   generatedList.addAll(addAdvantages(noOfDisadvsi, AdvType.disadvantage, generatedList));
   generatedList.addAll(addAdvantages(noOfPerksi, AdvType.perk, generatedList));
   generatedList.addAll(addAdvantages(noOfQuirksi, AdvType.quirk, generatedList));
 
   return generatedList;
 }

 public List<String> generateFeatures() {
   // TODO features aren't gender specific yet
   MoreFeatures mf = new MoreFeatures();
   return mf.getGeneratedFeaturesList(gender);
 }
 
 public String generateFeature(FeatureType ft) {
   MoreFeatures mf = new MoreFeatures();
   return mf.generateOneFeature(ft);
 }

 private List<Advantage> addAdvantages(int numberOf, AdvType aType,
     List<Advantage> generatedList) {
   List<Advantage> as = new ArrayList<Advantage>();
   for (int i = 0; i < numberOf; i++) {
     boolean done = false;
     while (!done) {
       done = addNoDups(generateOne(aType), generatedList);
     }
   }
   return as;
 }

 private boolean addNoDups(Advantage a, List<Advantage> advs) {
   boolean added;
   if (!advs.contains(a)) {
     advs.add(a);
     added = true;
   } else {
     added = false;
   }
   return added;
 }

 private List<Advantage> filterOnly(AdvType at) {
   List<Advantage> filteredList = new ArrayList<Advantage>();
   switch (at) {
   case advantage:
     for (Advantage a : Assets.advantages) {
       boolean addMe = false;
       if (Assets.noForbidden) {
         if (a.getIsForbidden()) {
           addMe = false;
         } else {
           addMe = true;
         }
       } else {
         addMe = true;
       }
       if (addMe) {
         if (a.getAorD().equalsIgnoreCase("A")
             && a.getCalcCost() != 1) {
           filteredList.add(a);
         }
       }
     }
     break;
   case disadvantage:
     for (Advantage d : Assets.advantages) {
       boolean addMe = false;
       if (Assets.noForbidden) {
         if (d.getIsForbidden()) {
           addMe = false;
         } else {
           addMe = true;
         }
       } else {
         addMe = true;
       }
       if (addMe) {
         if (d.getAorD().equalsIgnoreCase("D")
             && d.getCalcCost() != -1) {
           filteredList.add(d);
         }
       }
     }
     break;
   // TODO fix perk/quirk in csv file
   case perk:
     for (Advantage p : Assets.advantages) {
       boolean addMe = false;
       if (Assets.noForbidden) {
         if (p.getIsForbidden()) {
           addMe = false;
         } else {
           addMe = true;
         }
       } else {
         addMe = true;
       }
       if (addMe) {
         if (p.getAorD().equalsIgnoreCase("A")
             && p.getCalcCost() == 1) {
           filteredList.add(p);
         }
       }
     }
     break;
   case quirk:
     for (Advantage q : Assets.advantages) {
       boolean addMe = false;
       if (Assets.noForbidden) {
         if (q.getIsForbidden()) {
           addMe = false;
         } else {
           addMe = true;
         }
       } else {
         addMe = true;
       }
       if (addMe) {
         if (q.getAorD().equalsIgnoreCase("D")
             && q.getCalcCost() == -1) {
           filteredList.add(q);
         }
       }
     }
     break;
   }
   return filteredList;
 }

 public Advantage generateOne(AdvType at) {
   Random r = new Random();
   Advantage returnAdvantage = null;
   switch (at) {
   case advantage:
     List<Advantage> advsOnly = filterOnly(AdvType.advantage);
     int totalAdvs = advsOnly.size();
     returnAdvantage = advsOnly.get(r.nextInt(totalAdvs));
     break;
   case disadvantage:
     List<Advantage> disadvsOnly = filterOnly(AdvType.disadvantage);
     int totalDisAdvs = disadvsOnly.size();
     returnAdvantage = disadvsOnly.get(r.nextInt(totalDisAdvs));
     break;
   case perk:
     List<Advantage> perksOnly = filterOnly(AdvType.perk);
     int totalPerks = perksOnly.size();
     returnAdvantage = perksOnly.get(r.nextInt(totalPerks));
     break;
   case quirk:
     List<Advantage> quirksOnly = filterOnly(AdvType.quirk);
     int totalQuirks = quirksOnly.size();
     returnAdvantage = quirksOnly.get(r.nextInt(totalQuirks));
     break;
   }
   // int totalAll = Assets.advantages.size();
   return returnAdvantage;
 }
}
