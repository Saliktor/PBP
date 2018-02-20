import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';


import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { UserService } from './user.service';
import { AppRoutingModule } from './app-routing.module';
import { HomeComponent } from './home/home.component';
import { ProfileComponent } from './profile/profile.component';
import { GameComponent } from './game/game.component';
import { GameService } from './game.service';
import { MessageItemComponent } from './chatbox/message-item/message-item.component';
import { MessageListComponent } from './chatbox/message-list/message-list.component';
import { MessageFormComponent } from './chatbox/message-form/message-form.component';
import { GetMessagesService } from './get-messages.service';



@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    HomeComponent,
    ProfileComponent,
    GameComponent,
    MessageItemComponent,
    MessageListComponent,
    MessageFormComponent
  ],
  imports: [
    BrowserModule,
    HttpModule,
    FormsModule,
    AppRoutingModule
  ],
  providers: [UserService, GameService, GetMessagesService],
  bootstrap: [AppComponent]
})
export class AppModule { }
