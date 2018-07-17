import { Component, OnInit } from '@angular/core';
import { Member } from '../../member/member';
import { MemberService } from '../../member/member.service';
import { MemNode } from './mem-node';

@Component({
  selector: 'app-member',
  templateUrl: './member.component.html',
  styleUrls: ['./member.component.css']
})
export class MemberComponent implements OnInit {

  member: MemNode[];
  members: Member[];

  constructor(private memberService: MemberService) { }

  ngOnInit() {
    this.getMembers();
  }

  getMembers(): void {
    this.member = [];
    this.memberService.getMembers()
      .subscribe(response => {
        this.members = response;
        for (const mem of this.members) {
          const memberNode = this.buildMemberNode(mem);
          this.member.push(memberNode);
        }
      });
  }

  buildMemberNode(member: Member): MemNode {
    const memberNode = new MemNode(member.id, member.firstName);
    return memberNode;
  }

  add(firstName: string) {
    firstName = firstName.trim();
    if (!firstName) { return; }
    this.memberService.addMember({ firstName } as Member).subscribe(mem => this.members.push(mem));
  }

  delete(member: Member): void {
    this.members = this.members.filter(m => m !== member);
    this.memberService.deleteMember(member).subscribe();
  }
}
