---
services: active-directory
platforms: java
author: Manish Sharma
---

# Integrating Azure AD into a Java Spring MVC using username and password

This sample demonstrates sign-in without redirecting to Azure Active Directory Web Page and perform the sign-in using web API that is secured using Azure AD. The Java application uses the Active Directory Authentication Library for Java (ADAL4J) to obtain a JWT access token through the OAuth 2.0 protocol. The access token is sent to the web API to authenticate the user. This sample shows you how to use ADAL to authenticate users via raw credentials (username and password) via a text-only interface.


## Quick Start

Getting started with the sample is easy. It is configured to run out of the box with minimal setup.

### Step 1: Register an Azure AD Tenant

To use this sample you will need a Azure Active Directory Tenant. If you're not sure what a tenant is or how you would get one, read [What is an Azure AD tenant](http://technet.microsoft.com/library/jj573650.aspx)? or [Sign up for Azure as an organization](http://azure.microsoft.com/documentation/articles/sign-up-organization/). These docs should get you started on your way to using Azure AD.

### Step 2: Download Java (7 and above) & Tomcat (8 and above) for your platform 

To successfully use this sample, you need a working installation of [Java](http://www.oracle.com/technetwork/java/javase/downloads/index.html), [Maven](https://maven.apache.org) & [Tomcat](http://tomcat.apache.org/download-80.cgi).

### Step 3: Download the Sample application and modules

Next, clone the sample repo and install the project's dependencies.

From your shell or command line:

* `$ git clone https://github.com/mannu2050/active-directory-java-springmvc-headless.git`
* `$ cd active-directory-java-springmvc-headless`

### Step 4: Register the GraphAPI-Headless-Java app

* Sign in to the Azure management portal.
* Click on Active Directory in the left hand nav.
* Click the directory tenant where you wish to register the sample application.
* Click the Applications tab.
* In the drawer, click Add.
* Click "Add an application my organization is developing".
* Enter a friendly name for the application, for example "GraphAPI-Headless-Java" then select "Web application and/or web API", and click next.
* For the Redirect URI, enter the application url e.g. http://localhost:8080. Please note that the Redirect URI will not be used in this sample, but it needs to be defined nonetheless. Click finish.
* Click the Configure tab of the application.
* Find the Client ID value and copy it aside, you will need this later when configuring your application.
* In "Permissions to Other Applications", ensure "Windows Azure Active Directory" is selected. Select "Sign in and read user profile" from the "Delegated Permissions" dropdown and ensure it is checked. This will be the permission we'll be using in the sample.
You can also refer [here](https://msdn.microsoft.com/office/office365/howto/add-common-consent-manually#bk_RegisterWebApp) for detailed instruction on how to register application in Azure Active Directory.

### Step 5: Navigate to \src\com\headlessauthentication\controller & Configure `headlessauth.java`

Enter the client id, username & password at the top of the `headlessauth.java` file.

```java
public class headlessauth {

    private String AUTHORITY = "https://login.microsoftonline.com/common/";
    private String CLIENT_ID = "<your client id>";
	private String username = "<your username>";
	private String password = "<your password>";			
```    

### Step 6: Package and then deploy the headlessauthentication-0.0.1-SNAPSHOT.war` file to tomcat.

From your shell or command line:

* `$ mvn package`

This will generate a `headlessauthentication-0.0.1-SNAPSHOT.war` file in your /targets directory. Now copy this war to webapps folder of tomcat & open browser then specify:

* http://localhost:8080/headlessauthentication/

Then click on hyperlink displayed at the page which will navigate you to ../authenticate/

### You're done!

The authenticate controller will authenticate the credentials specified in the class headlessauth and show you the Access token, Refresh token & ID token on successful authentication.
