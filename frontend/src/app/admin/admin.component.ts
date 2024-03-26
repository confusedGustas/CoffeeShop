import {Component, OnInit} from '@angular/core';
import {AdminService} from "../_services/admin.service";
import {AppComponent} from "../app.component";
import {FormControl, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";

@Component({
  selector: 'app-admin',
  standalone: true,
  imports: [
    ReactiveFormsModule
  ],
  templateUrl: './admin.component.html',
})
export class AdminComponent implements OnInit {
  isLoggedIn: boolean = false;
  menuItems: any[] = [];
  showModal = false;
  productId = -1;
  showPasswordModal = false;

  CoffeeForm = new FormGroup({
    name: new FormControl('', [Validators.required]),
    description: new FormControl('', [Validators.required]),
    price: new FormControl('', [Validators.required, Validators.pattern("^[0-9]+(\.[0-9]{1,2})?$")]),
  });
  SnacksForm = new FormGroup({
    name: new FormControl('', [Validators.required]),
    description: new FormControl('', [Validators.required]),
    price: new FormControl('', [Validators.required, Validators.pattern("^[0-9]+(\.[0-9]{1,2})?$")]),
  });
  DesertsForm = new FormGroup({
    name: new FormControl('', [Validators.required]),
    description: new FormControl('', [Validators.required]),
    price: new FormControl('', [Validators.required, Validators.pattern("^[0-9]+(\.[0-9]{1,2})?$")]),
  });
  EditForm = new FormGroup({
    name: new FormControl('', [Validators.required]),
    description: new FormControl('', [Validators.required]),
    price: new FormControl('', [Validators.required, Validators.pattern("^[0-9]+(\.[0-9]{1,2})?$")]),
  });
  ChangePasswordForm = new FormGroup({
    oldPassword: new FormControl('', [Validators.required]),
    newPassword: new FormControl('', [Validators.required]),
  });

  constructor(private adminService: AdminService,
              private appComponent: AppComponent) { }

  ngOnInit(): void {
    this.adminService.verify().subscribe({
      next: () => {
        this.isLoggedIn = true;
        this.adminService.getMenu().subscribe({
          next: (data: any) => this.menuItems = data
        });
      }, error: () => this.appComponent.redirect('/login')
    })
  }

  logout() {
    this.adminService.logout().subscribe({
      next: () => {
        this.isLoggedIn = false;
        this.appComponent.redirect('/');
      },
      error: () => {
        this.isLoggedIn = false;
        this.appComponent.redirect('/');
      }
    });
  }

  add(category: string) {
    if (category == 'Coffee') {
      const { name, description, price } = this.CoffeeForm.value;
      if (name && description && price) {
        this.addProduct(category, name, description, parseFloat(price));
      }
    } else if (category == 'Snacks') {
      const { name, description, price } = this.SnacksForm.value;
      if (name && description && price) {
        this.addProduct(category, name, description, parseFloat(price));
      }
    } else if (category == 'Desserts') {
      const { name, description, price } = this.DesertsForm.value;
      if (name && description && price) {
        this.addProduct(category, name, description, parseFloat(price));
      }
    }
  }

  addProduct(category: string, name: string, description: string, price: number) {
    this.adminService.add(category, name, description, price).subscribe({
      next: () => { location.reload(); },
      error: () => {
        this.adminService.verify().subscribe({
          next: () => location.reload(),
          error: () => this.appComponent.redirect('/login')
        });
      }
    });
  }

  delete(id: number) {
    this.adminService.delete(id).subscribe({
      next: () => { location.reload() },
      error: () => {
        this.adminService.verify().subscribe({
          next: () => location.reload(),
          error: () => this.appComponent.redirect('/login')
        });
      }
    });
  }

  toggleModalOn(id: number){
    this.showModal = !this.showModal;
    this.productId = id;
  }

  toggleModalOff(){
    this.showModal = !this.showModal;
    this.productId = -1;
  }

  submitEdit() {
    const { name, description, price } = this.EditForm.value;
    if (name && description && price) {
      this.adminService.edit(this.productId, name, description, parseFloat(price)).subscribe({
        next: () => { location.reload(); },
        error: () => {
          this.adminService.verify().subscribe({
            next: () => location.reload(),
            error: () => this.appComponent.redirect('/login')
          });
        }
      });
    }
  }

  togglePasswordModal() {
    this.showPasswordModal = !this.showPasswordModal;
  }

  changePassword() {
    const { oldPassword, newPassword } = this.ChangePasswordForm.value;

    if (oldPassword && newPassword) {
      this.adminService.changePassword(oldPassword, newPassword).subscribe({
        next: () => window.location.reload(),
        // error: () => window.location.reload()
      });
    }
  }
}
