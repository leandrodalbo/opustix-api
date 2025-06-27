CREATE TABLE venue (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    address VARCHAR(255),
    created_at BIGINT NOT NULL
);

CREATE TABLE event (
    id UUID PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description VARCHAR(1024),
    start_time BIGINT NOT NULL,
    end_time BIGINT NOT NULL,
    capacity INT NOT NULL,
    venue_id UUID NOT NULL,
    created_at BIGINT NOT NULL,
    CONSTRAINT fk_event_venue
    FOREIGN KEY (venue_id)
    REFERENCES venue(id) ON DELETE CASCADE
);

CREATE TABLE ticket_type (
    id UUID PRIMARY KEY,

    event_id UUID NOT NULL,
    name VARCHAR(100) NOT NULL,
    price DOUBLE PRECISION NOT NULL,
    currency VARCHAR(3),

    sale_start BIGINT,
    sale_end BIGINT,
    quantity INT,
    description TEXT,

    created_at BIGINT NOT NULL,

    CONSTRAINT fk_event
    FOREIGN KEY (event_id)
    REFERENCES event(id) ON DELETE CASCADE
);

CREATE TABLE event_sector (
    id UUID PRIMARY KEY,
    event_id UUID NOT NULL,

    name VARCHAR(100) NOT NULL,
    description TEXT,
    price_addition DOUBLE PRECISION,

    created_at BIGINT NOT NULL,

    CONSTRAINT fk_event_sector_event
    FOREIGN KEY (event_id)
    REFERENCES event(id) ON DELETE CASCADE
);

CREATE TABLE event_seat (
    id UUID PRIMARY KEY,

    event_id UUID NOT NULL,
    sector_id UUID,

    label VARCHAR(50) NOT NULL,
    seat_row_info VARCHAR(20),
    seat_number VARCHAR(20),

    price_addition DOUBLE PRECISION,

    created_at BIGINT NOT NULL,

    CONSTRAINT fk_event_seat_event FOREIGN KEY (event_id) REFERENCES event(id) ON DELETE CASCADE,
    CONSTRAINT fk_event_seat_sector FOREIGN KEY (sector_id) REFERENCES event_sector(id) ON DELETE SET NULL
);

CREATE TABLE purchase (
    id UUID PRIMARY KEY,

    user_info TEXT NOT NULL,
    total_price DOUBLE PRECISION NOT NULL,
    payment_status VARCHAR(20) NOT NULL,

    expires_at BIGINT,
    created_at BIGINT NOT NULL
);

CREATE TABLE reservation (
    id UUID PRIMARY KEY,

    purchase_id UUID NOT NULL,
    event_id UUID NOT NULL,
    ticket_type_id UUID NOT NULL,

    sector_id UUID,
    seat_id UUID,

    price DOUBLE PRECISION NOT NULL,
    status VARCHAR(20) NOT NULL,

    created_at BIGINT NOT NULL,


    CONSTRAINT fk_reservation_purchase FOREIGN KEY (purchase_id) REFERENCES purchase(id) ON DELETE CASCADE,
    CONSTRAINT fk_reservation_event FOREIGN KEY (event_id) REFERENCES event(id) ON DELETE CASCADE,
    CONSTRAINT fk_reservation_ticket_type FOREIGN KEY (ticket_type_id) REFERENCES ticket_type(id) ON DELETE CASCADE,
    CONSTRAINT fk_reservation_sector FOREIGN KEY (sector_id) REFERENCES event_sector(id) ON DELETE SET NULL,
    CONSTRAINT fk_reservation_seat FOREIGN KEY (seat_id) REFERENCES event_seat(id) ON DELETE SET NULL
);

CREATE TABLE ticket (
    id UUID PRIMARY KEY,
    reservation_id UUID NOT NULL REFERENCES reservation(id) ON DELETE CASCADE,
    event_id UUID NOT NULL REFERENCES event(id) ON DELETE CASCADE,
    ticket_type_id UUID NOT NULL REFERENCES ticket_type(id),
    sector_id UUID REFERENCES event_sector(id),
    seat_id UUID REFERENCES event_seat(id),
    user_info TEXT  NOT NULL,
    qr_code_data TEXT NOT NULL UNIQUE,
    issued_at BIGINT NOT NULL,
    scanned_at BIGINT
);


CREATE UNIQUE INDEX uq_reservation_event_seat
ON reservation(event_id, seat_id)
WHERE seat_id IS NOT NULL AND status IN ('PENDING', 'CONFIRMED');
