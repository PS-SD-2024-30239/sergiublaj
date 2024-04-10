import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { AuthService } from '../../../core/service/auth/auth.service';
import { UserService } from '../../../core/service/user/user.service';
import { LoginModel } from '../../../shared/models/login.model';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent implements OnInit, OnDestroy {

  loginForm: FormGroup = new FormGroup({});
  errorMessage?: string;
  loginSubscription?: Subscription;
  getInfoSubscription?: Subscription;

  constructor(
    private formBuilder: FormBuilder,
    private authService: AuthService,
    private userService: UserService,
    private router: Router
  ) {
  }

  ngOnInit(): void {
    this.buildLoginForm();
  }

  ngOnDestroy(): void {
    this.unsubscribeFromSubscribers();
  }

  private buildLoginForm(): void {
    this.loginForm = this.formBuilder.group({
      email: ['', [Validators.required, Validators.email]],
      password2: ['', [Validators.required]]
    });
  }

  private unsubscribeFromSubscribers(): void {
    this.loginSubscription?.unsubscribe();
    this.getInfoSubscription?.unsubscribe();
  }

  login(): void {
    if (!this.loginForm?.valid) {
      this.errorMessage = 'Invalid form completion!';
      setTimeout(() => this.errorMessage = undefined, 3000);
      return;
    }

    const credentials: LoginModel = {
      email: this.loginForm?.get('email')?.value,
      password: this.loginForm?.get('password2')?.value
    };
    this.loginSubscription = this.authService.login(credentials).subscribe({
      next: () => this.getUserInfo(),
      error: () => this.errorMessage = 'Invalid credentials'
    });
  }

  private getUserInfo(): void {
    this.getInfoSubscription = this.userService.getInfo().subscribe(response => {
      localStorage.setItem('loggedUser', JSON.stringify(response));
      this.router.navigateByUrl('/dashboard/chefs');
    });
  }
}
