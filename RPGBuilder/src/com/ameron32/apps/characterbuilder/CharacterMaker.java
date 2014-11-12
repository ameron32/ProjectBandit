package com.ameron32.apps.characterbuilder;

import java.util.List;

public interface CharacterMaker {
  public CharacterMaker fromBlank();
  public CharacterMaker fromCharacter(Character character);
  public CharacterMaker generate(List<Attribute> attrs);
  public CharacterMaker generate(Attribute attr);
  public Character create();
  
  public enum Attribute {
    ST, DX, IQ, HT, 
    HP, Will, Per, FP, Dodge, BM, BL, BS, cost,
    name, gender, job, 
    advantage1, advantageSome, advantageAll,
    feature1, featureSome, featureAll
  }
}
