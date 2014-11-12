package com.parse.starter.util;

import com.parse.GetCallback;
import com.parse.ParseInstallation;
import com.parse.ParseQuery;

public class InstallationUtils {
  
  public static void detectUnauthorizedInstallation(GetCallback<ParseInstallation> callback) {
    ParseInstallation currentInstallation = ParseInstallation.getCurrentInstallation();
    ParseQuery<ParseInstallation> installations = new ParseQuery<ParseInstallation>("_Installation");
    installations.getInBackground(currentInstallation.getObjectId(), callback);
  }
}
