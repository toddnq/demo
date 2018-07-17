import { Injectable } from '@angular/core';
import { Member } from './member';
import { Observable, of } from '../../../node_modules/rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class MemberService {

  constructor(private http: HttpClient) { }
  private membersUrl = 'http://' + window.location.hostname + ':8080/api/employee/';

  getMembers(): Observable<Array<Member>> {
    return this.http.get<Array<Member>>(this.membersUrl);
  }

  getMember(id: number): Observable<Member> {
    const url = `${this.membersUrl}/${id}`;
    return this.http.get<Member>(url);
  }

  updateMember(member: Member): Observable<any> {
    return this.http.put(this.membersUrl, member, httpOptions);
  }

  addMember(member: Member): Observable<Member> {
    return this.http.post<Member>(this.membersUrl, member, httpOptions)
  };

  deleteMember(member: Member | number): Observable<Member> {
    const id = typeof member === 'number' ? member : member.id;
    const url = `${this.membersUrl}/${id}`;

    return this.http.delete<Member>(url, httpOptions)
  };

  searchMembers(term: string): Observable<Member[]> {
    if (!term.trim()) {
      return of([]);
    }
    return this.http.get<Member[]>(`${this.membersUrl}/?name=${term}`);
  };
}