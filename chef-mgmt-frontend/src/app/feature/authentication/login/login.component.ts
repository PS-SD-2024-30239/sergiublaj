import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AuthService} from "../../../core/service/auth/auth.service";
import {UserService} from "../../../core/service/user/user.service";
import {Router} from "@angular/router";
import {LoginModel} from "../../../shared/models/login.model";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup = new FormGroup({});
  errorMessage?: string;

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

  private buildLoginForm(): void {
    this.loginForm = this.formBuilder.group({
      email: ['', [Validators.required, Validators.email]],
      password2: ['', [Validators.required]]
    });
  }

  login(): void {
    if (!this.loginForm?.valid) {
      this.errorMessage = 'Invalid form completion!';
      setTimeout(() => this.errorMessage = undefined, 3000);
      return;
    }

    const credentials: LoginModel = {
      email: this.loginForm?.get('email')?.value,
      password: this.loginForm?.get('password2')?.value,
    }
    this.authService.login(credentials).subscribe({
      next: () => this.getUserInfo(),
      error: () => this.errorMessage = 'Invalid credentials'
    });
  }

  private getUserInfo(): void {
    this.userService.getInfo().subscribe(response => {
      localStorage.setItem('loggedUser', JSON.stringify(response));
      this.router.navigateByUrl('/dashboard/chefs')
    })
  }
}
