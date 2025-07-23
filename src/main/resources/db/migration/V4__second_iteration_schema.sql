ALTER TABLE event_sector
DROP CONSTRAINT fk_event_sector_event;

ALTER TABLE event_sector
DROP COLUMN event_id;

ALTER TABLE event_sector
ADD COLUMN ticket_type_id UUID NOT NULL;

ALTER TABLE event_sector
ADD CONSTRAINT fk_event_sector_ticket_type
FOREIGN KEY (ticket_type_id)
REFERENCES ticket_type(id) ON DELETE CASCADE;

ALTER TABLE event_sector
DROP COLUMN price_addition;


ALTER TABLE event_seat
DROP CONSTRAINT fk_event_seat_event;

ALTER TABLE event_seat
DROP COLUMN event_id;

ALTER TABLE event_seat
ALTER COLUMN sector_id SET NOT NULL;

ALTER TABLE event_seat
DROP COLUMN seat_row_info;

ALTER TABLE event_seat
DROP COLUMN price_addition;

