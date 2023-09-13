import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UserRegistrationRequest } from '../../models/user-registration-request';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment.development';
import { AuthenticationResponse } from '../../models/AuthenticationResponse';
import { AdminDTO } from 'src/app/models/admin-dto';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private readonly userUrl = `${environment.api.baseUrl}/${environment.api.registerUserUrl}`;
  private readonly adminUrl = `${environment.api.baseUrl}/${environment.api.registerAdminUrl}`;

  constructor(private httpClient: HttpClient) { }


  saveUser(userRegistartionRequest: UserRegistrationRequest): Observable<void> {
    return this.httpClient.post<void>(this.userUrl, userRegistartionRequest);
  }

  saveAdmin(userRegistartionRequest: UserRegistrationRequest): Observable<void> {
    return this.httpClient.post<void>(this.adminUrl, userRegistartionRequest);
  }

  isUserLogged() {
    const storedUser = localStorage.getItem('user');
    return (storedUser) ? true : false;
  }

  isUserAdmin() {
    const storedUser = localStorage.getItem('user');
    if (storedUser) {
      const authResponse: AuthenticationResponse = JSON.parse(storedUser);
      if (authResponse && authResponse.userDTO && authResponse.userDTO.roles) {
        const roles = authResponse.userDTO.roles;
        const roleAdminFounded = roles.find(item => item === 'ROLE_ADMIN');
        if (roleAdminFounded) {
          return true;
        }
      }
    }
    return false;
  }


  findAllAdmins(): Observable<AdminDTO[]> {
    return this.httpClient.get<AdminDTO[]>(this.adminUrl);
  }

  deleteAdmin(adminId: number | undefined): Observable<void> {
    return this.httpClient.delete<void>(`${this.adminUrl}/${adminId}`);
  }
}
