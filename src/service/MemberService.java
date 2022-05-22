package service;

import java.util.List;

import dao.MemberDao;
import model.Member;

public class MemberService {
	
	//Dao ��ü�� ����� �����鼭 ���
	MemberDao memberDao;
	
	public MemberService() {
		// ������ => �޼ҵ� �̸��� Ŭ���� �̸��� ����
		// ��ü�� �����ɶ� ����Ǵ� �ڵ�
		// new MemberService() ==> ������ �ڵ� ����
		memberDao = MemberDao.getInstance();
	}
	
	// �α��� ���� : true �� ������
	// �α��� ���� : false �� ������
	public boolean login(String id, String pw) {
		//1. �����ͺ��̽����� �Է¹��� ���̵�� ������ ȸ�������� ��ȸ
		Member member = memberDao.selectOne(id);
		
		if(member != null) {
			// member�� null �� �ƴϸ� �ش� ���̵� ���� ȸ�������� �ִ�.
			if(member.getPw().equals(pw)) {
				// �α��� ����
				return true;
			} else {
				// ��й�ȣ�� Ʋ�� => �α��� ����
				return false;
			}
		} else {
			// member �� null ==> ���̵� �������� ����
			// �α��� ����
			return false;
		}
	}
	
	// ȸ�� ���� ���� ==> true ����
	// ȸ�� ���� ���� ==> false ����
	public boolean join(Member member) {
		int insertResult = memberDao.insertMember(member);
		// ȸ������ �����ϸ� insertResult ���� 1�� �� �ִ�.
		if(insertResult == 1) {
			// ����
			return true;
		} 
		//����
		return false;
	}
	
	// ȸ�� �������� ���� ==> true ����
	// ȸ�� �������� ���� ==> false ����
	public boolean modify(Member member) {
		int updateResult = memberDao.updateMember(member);
		
		boolean result = false; // �������� ��
		if(updateResult > 0) {
			// ȸ������ ���� ����
			// updateMember() �޼ҵ��� ���� ����� ������ ���� ���� ����
			// ������ ȸ�� ������ ������ 0�� ���� ũ��.
			result = true;
		}
		
		return result;
	}
	
	// ȸ�� ���� ������ ����
	public Member getMember(String id) {
		return memberDao.selectOne(id);
	}
	
	// ȸ�� ��� �����ֱ� ����
	public List<Member> getAllMembers(){
		return memberDao.selectAll();
	}
}
