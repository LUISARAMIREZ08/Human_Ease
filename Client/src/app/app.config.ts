import { ApplicationConfig } from '@angular/core';
import { provideRouter } from '@angular/router';

import { routes } from './app.routes';
import { provideClientHydration } from '@angular/platform-browser';
import { provideHttpClient, withInterceptors } from '@angular/common/http';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import { authInterceptor } from './services/auth.interceptor';
import { initializeApp, provideFirebaseApp } from '@angular/fire/app';
import { getStorage, provideStorage } from '@angular/fire/storage';
import { AngularFireStorageModule } from '@angular/fire/compat/storage';
import { FIREBASE_OPTIONS } from '@angular/fire/compat';


export const appConfig: ApplicationConfig = {
  providers: [provideRouter(routes), provideClientHydration(), provideHttpClient(withInterceptors([authInterceptor])), provideAnimationsAsync(), provideFirebaseApp(() => initializeApp({"projectId":"human-ease","appId":"1:1012943893604:web:c249ddcf1152b722191b71","storageBucket":"human-ease.appspot.com","apiKey":"AIzaSyATRAv7C-hAPwDeCEgh9DL6loFcM_4WL2g","authDomain":"human-ease.firebaseapp.com","messagingSenderId":"1012943893604","measurementId":"G-N5SSDSBKGQ"})), provideStorage(() => getStorage()),
  { provide: FIREBASE_OPTIONS, useValue: {"projectId":"human-ease","appId":"1:1012943893604:web:c249ddcf1152b722191b71","storageBucket":"human-ease.appspot.com","apiKey":"AIzaSyATRAv7C-hAPwDeCEgh9DL6loFcM_4WL2g","authDomain":"human-ease.firebaseapp.com","messagingSenderId":"1012943893604","measurementId":"G-N5SSDSBKGQ"} }]
};
