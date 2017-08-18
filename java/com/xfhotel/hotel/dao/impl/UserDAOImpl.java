package com.xfhotel.hotel.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.xfhotel.hotel.entity.Site;
import com.xfhotel.hotel.entity.User;

@Repository
public class UserDAOImpl extends BaseDAOImpl<User, Long> {
}
