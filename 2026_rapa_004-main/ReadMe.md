# ORM에서의 연관관계의 종류
-> 방향성, 연관관계의 주인(Relationship Owner)
1. N:1 -> `@ManyToOne`
2. 1:N -> `@OneToMany`
3. 1:1 -> `@OneToOne`
4. N:M -> `@ManyToMany`

## 쇼핑몰
- [x] Member
  - [x] Address
- [x] Product
  - [x] Category
- [x] Order
  - [x] OrderItem 
